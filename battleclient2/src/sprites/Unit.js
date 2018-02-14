import Phaser from 'phaser'
import FloatingText from '../FloatingText';

export default class extends Phaser.Sprite {


    constructor(game, { x = 0, y = 0, scale = 1, teamType = "ATK", heroType = "MELEE", life = 100, asset = '', timeBetweenAction = 1500}) {
        super(game, x, y, asset);

        this.healthBarWidth = 100;
        this.specialBarWidth = 100;

        this.timeBetweenAction = timeBetweenAction;

        this.sx = x
        this.sy = y
        this.sscale = scale

        this.teamType = teamType;
        this.heroType = heroType;

        this.life = life;
        this.currLife = life;

        this.scale.set(scale);
        this.position.x = x;
        this.position.y = y;
        this.animations.add('idle', [0, 1, 2, 3, 4]);
        this.animations.add('run', [5, 6, 7, 8, 9]);
        this.animations.add('attack', [10, 11, 12, 13, 14]);
        this.animations.add('hurt', [15, 16, 17, 18, 19]);
        this.animations.add('die', [20, 21, 22, 23, 24]);

        this.anchor.set(0.5, 0);

        if (this.teamType === "DEF") {
            this.scale.x *= -1
        }

        var healthBarBg = game.add.bitmapData(this.healthBarWidth, 5);
        healthBarBg.ctx.beginPath();
        healthBarBg.ctx.rect(0, 0, this.healthBarWidth, 5);
        healthBarBg.ctx.fillStyle = '#ff0000';
        healthBarBg.ctx.fill();

        this.healthBarBg = game.add.sprite(this.sx - 50, this.sy - 10, healthBarBg);

        var healthBar = game.add.bitmapData(this.healthBarWidth, 5);
        healthBar.ctx.beginPath();
        healthBar.ctx.rect(0, 0, this.healthBarWidth, 5);
        healthBar.ctx.fillStyle = '#00ff00';
        healthBar.ctx.fill();

        this.healthBar = game.add.game.add.sprite(this.sx - 50, this.sy - 10, healthBar);

        var specialBar = game.add.bitmapData(this.specialBarWidth, 3);
        specialBar.ctx.beginPath();
        specialBar.ctx.rect(0, 0, this.specialBarWidth, 3);
        specialBar.ctx.fillStyle = '#0ffff0';
        specialBar.ctx.fill();

        this.specialBar = game.add.game.add.sprite(this.sx - 50, this.sy - 5, specialBar);
        this.specialBar.width = this.specialBarWidth / 2;


        this.playAnimationByName("idle");

    }

    playAnimationByName(name, repeat = true) {
        this.animations.play(name, 24, repeat);
        this.animations.currentAnim.speed = 10;
    }

    updateSpecial(value) {

        this.specialBar.width = this.specialBarWidth * value / 100;
        if (this.specialBar.width < 0) {
            this.specialBar.width = 0;
        } else if (this.specialBar.width > this.specialBarWidth) {
            this.specialBar.width = this.specialBarWidth;
        }

    }

    action(target, log, targetData) {

        this.playAnimationByName('attack', false);
        this.animations.currentAnim.onComplete.addOnce(function () {
            this.playAnimationByName('idle');
        }, this);

        var pos = (this.heroType === 'RANGED' || log.battleEvent.actionType === 'HEAL' ? this : target);

        this.updateSpecial(log.heroOrigin.energy);

        let tween = this.game.add.tween(this).to({ x: pos.position.x, y: pos.position.y }, this.timeBetweenAction, Phaser.Easing.Linear.None, true, 100);
        tween.onComplete.addOnce(() => {

            this.position.x = this.sx;
            this.position.y = this.sy;

            target.updateSpecial(targetData.energy);

            target.receivedAction(log.battleEvent, targetData.hero.currHp);
        });
    }

    receivedAction(event, newLife) {

        new FloatingText(this.game, {
            text: event.value,
            animation: "up",
            textOptions: {
                font: "Bangers",
                fontSize: 18,
                fill: event.value > 0 ? "#00FF00" : event.damageType === "MAGIC" ? "#0000FF" : "#FF0000",
                stroke: "#000000",
                strokeThickness: 2,
            },
            x: this.position.x,
            y: this.position.y + 20,
            timeToLive: 700 // ms
        });

        this.currLife = newLife;

        this.healthBar.width = this.healthBarWidth * this.currLife / this.life;
        if (this.healthBar.width < 0) {
            this.healthBar.width = 0;
        } else if (this.healthBar.width > this.healthBarWidth) {
            this.healthBar.width = this.healthBarWidth;
        }

        if (this.currLife <= 0) {
            this.die();
        } else {
            this.playAnimationByName('hurt', false);
            this.animations.currentAnim.onComplete.addOnce(function () {
                this.playAnimationByName('idle');
            }, this);
        }

    }

    die() {
        this.playAnimationByName("die", false);
        this.animations.currentAnim.onComplete.addOnce(function () {
            this.animations.stop();
        }, this);

    }

}
