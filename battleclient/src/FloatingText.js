import Phaser from 'phaser';

export default class {

    constructor(state, options) {
        this.state = state;
        this.options = options;
        this._obj = {};

        this.floatingTextGroup = this.state.add.group();
        
        var easings = [
            Phaser.Easing.Cubic.InOut,
            Phaser.Easing.Sinusoidal.In,
            Phaser.Easing.Quadratic.InOut,
            Phaser.Easing.Quartic.Out,
            Phaser.Easing.Linear,
            Phaser.Easing.Cubic.In,
            Phaser.Easing.Quintic.Out,
            Phaser.Easing.Quintic.InOut
        ];

        if (this.options.numOfItems !== undefined) {
            this.createPool();
        } else {
            this.createFloatingText();
        }
    }

    createFloatingText(store) {
        var _text = this.options.text || "";
        var _textOptions = this.options.textOptions || {
            fontSize: 12,
            fill: "#ffffff",
            stroke: "#1e1e1e",
            strokeThickness: 1,
            wordWrap: true,
            wordWrapWidth: 200
        };
        var _sprite = this.options.sprite || null;
        var _spriteAnimationName = this.options.spriteAnimationName || "";
        var _spriteAnimationFrames = this.options.spriteAnimationFrames || [];
        var _spriteAnimationFrameRate = this.options.spriteAnimationFrameRate || 24;
        var _spriteAnimationRepeat = this.options.spriteAnimationRepeat || true;
        var _spritePlayAnimationOnStart = this.options.playAnimationOnStart || true;
        var _spriteAnchor = this.options.spriteAnchor || 0.5;
        var _x = this.options.x || "auto";
        var _y = this.options.y || "auto";
        var _rotation = this.options.rotation || false;
        var _parentObj = this.options.parentObj || null;
        var _width = this.options.width || "auto";
        var _height = this.options.height || "auto";
        var _hasBackground = this.options.hasBackground || false;
        var _backgroundColor = this.options.backgroundColor || 0x000000;
        var _animation = this.options.animation || "explode"; // explode, smoke, custom, directional: up, down, left, right, fade, physics
        var _distance = this.options.distance || 40;
        var _easing = this.options.easing || Phaser.Easing.Quintic.Out;
        var _timeToLive = this.options.timeToLive || 600; // in ms
        var _fixedToCamera = this.options.fixedToCamera || false;
        var _align = this.options.align || "center";
        var _customPath = this.options.customPath || [];

        // create the element
        if (_sprite === null) {
            this._obj = this.state.add.text(0, 0, _text, _textOptions);
            //this._obj.anchor.setTo(_spriteAnchor);
        } else {
            this._obj = this.state.add.sprite(_sprite.x, _sprite.y, _sprite.key, _spriteAnimationFrames[0]);
            this._obj.anchor.setTo(_spriteAnchor);
            this._obj.animations.add(_spriteAnimationName, _spriteAnimationFrames, _spriteAnimationFrameRate, _spriteAnimationRepeat, true);
        }

        if (_rotation !== false) {
            this._obj.angle = _rotation;
        }

        if (_parentObj !== null && _parentObj !== undefined) {
            if (_x === "auto") {
                if (_parentObj.anchor === 0) {
                    this._obj.x = _parentObj.x + _parentObj.width / 2 - this._obj.width / 2;
                } else {
                    this._obj.x = _parentObj.x - this._obj.width / 2;
                }
            } else {
                this._obj.x = _x;
            }

            if (_y === "auto") {
                if (_parentObj.anchor === 0) {
                    this._obj.y = _parentObj.y + _parentObj.height / 2 - this._obj.height / 2;
                } else {
                    this._obj.y = _parentObj.y - this._obj.height / 2;
                }
            } else {
                this._obj.y = _y;
            }
        } else {
            if (_align === "center") {
                if (_sprite !== null) {
                    this._obj.x = _x - this._obj.width * _spriteAnchor / 2;
                    this._obj.y = _y - this._obj.height * _spriteAnchor / 2;
                } else {
                    this._obj.x = _x - this._obj.width / 2;
                    this._obj.y = _y - this._obj.height / 2;
                }
            } else if (_align === "left") {
                this._obj.x = _x;
                this._obj.y = _y;
            } else if (_align === "right") {
                this._obj.x = _x + this._obj.width;
                this._obj.y = _y + this._obj.height;
            } else if (_align === "none") {
                this._obj.x = _x;
                this._obj.y = _y;
            }
        }

        var modal;
        if (_hasBackground === true) {
            modal = this.state.add.graphics(this._obj.width + 10, this._obj.height);
            modal.beginFill(_backgroundColor, 1);
            //modal.x = this._obj.x - 5;
            //modal.y = this._obj.y - 5;
            this.floatingTextGroup.width = this._obj.width + 5;
            this.floatingTextGroup.height = this._obj.width + 5;
            modal.drawRoundedRect(0, 0, this._obj.width + 10, this._obj.height, 6);
            modal.endFill();
            this.floatingTextGroup.add(modal);
        } else {
            this.floatingTextGroup.width = this._obj.width;
            this.floatingTextGroup.height = this._obj.height;
        }

        if (_fixedToCamera === true) {
            this.floatingTextGroup.fixedToCamera = true;
        }

        this.floatingTextGroup.x = this._obj.x;
        this.floatingTextGroup.y = this._obj.y;
        this._obj.x = 0;
        this._obj.y = 0;
        if (modal !== undefined) {
            modal.x = -5;
            modal.y = 0;
        }
        this._obj._animation = _animation;
        this._obj._easing = _easing;
        this._obj._timeToLive = _timeToLive;
        this._obj._distance = _distance;
        this._obj._customPath = _customPath;
        this._obj._spriteAnimationName = _spriteAnimationName;
        this._obj._sprite = _sprite;
        this.floatingTextGroup.add(this._obj);
        this.floatingTextGroup.visible = false;

        if (store !== true) {
            this.animateFloatingText();
        } else {
            return this.floatingTextGroup;
        }
    }

    animateFloatingText() {

        var type = this._obj._animation;
        var tweenObj;
        var pointsX = [];
        var pointsY = [];
        var startX;
        var startY;
        var side;
        this.floatingTextGroup.visible = true;
        if (this._obj._sprite !== null) {
            this._obj.animations.play(this._obj._spriteAnimationName);
        }

        if (type === "physics") {
            startX = this.floatingTextGroup.x;
            startY = this.floatingTextGroup.y;
            side = this.state.rnd.integerInRange(0, 10);
            var firstBezierPointX;
            var firstBezierPointY;
            var secondBezierPointX;
            var secondBezierPointY;
            var endX;
            var endY;

            if (side > 5) {
                firstBezierPointX = this.floatingTextGroup.x + 25;
                firstBezierPointY = this.floatingTextGroup.y - 50;
                secondBezierPointX = this.floatingTextGroup.x + 50;
                secondBezierPointY = this.floatingTextGroup.y - 25;
                endX = this.floatingTextGroup.x + 100;
                endY = this.floatingTextGroup.y + 50;
            } else {
                firstBezierPointX = this.floatingTextGroup.x - 25;
                firstBezierPointY = this.floatingTextGroup.y - 50;
                secondBezierPointX = this.floatingTextGroup.x - 50;
                secondBezierPointY = this.floatingTextGroup.y - 25;
                endX = this.floatingTextGroup.x - 100;
                endY = this.floatingTextGroup.y + 50;
            }
            tweenObj = this.state.add.tween(this.floatingTextGroup).to({
                x: [startX, firstBezierPointX, secondBezierPointX, endX],
                y: [startY, firstBezierPointY, secondBezierPointY, endY],
            }, this._obj.timeToLive, Phaser.Easing.Circular.Out, true).interpolation(function (v, k) {
                return Phaser.Math.bezierInterpolation(v, k);
            });

            tweenObj.onComplete.addOnce(function () {
                var _tweenObj = this.tweenProperty(this, "alpha", {
                    alpha: 0
                }, 250, 0);

                _tweenObj.onComplete.addOnce(function () {
                    this.destroy();
                }, this);
            }, this.floatingTextGroup);

        } else if (type === "custom") {
            pointsX = [];
            pointsY = [];
            for (var i = 0; i < this._obj._customPath.length; i++) {
                pointsX.push(this._obj._customPath[i].x);
                pointsX.push(this._obj._customPath[i].y);
            }

            tweenObj = this.state.add.tween(this.floatingTextGroup).to({
                x: pointsX,
                y: pointsY,
            }, this._obj.timeToLive, Phaser.Easing.Circular.Out, true).interpolation(function (v, k) {
                return Phaser.Math.bezierInterpolation(v, k);
            });

            tweenObj.onComplete.addOnce(function () {
                var _tweenObj = this.tweenProperty(this, "alpha", {
                    alpha: 0
                }, 250, 0);

                _tweenObj.onComplete.addOnce(function () {
                    this.destroy();
                }, this);
            }, this.floatingTextGroup);
        } else if (type === "explode") {
            this.floatingTextGroup.pivot.setTo(0.5, 0.5);

            var textObj = this.floatingTextGroup.children[this.floatingTextGroup.children.length - 1];
            tweenObj = this.tweenProperty(textObj, "size", { fontSize: textObj.fontSize * 2 }, 250, 0, Phaser.Easing.Back.Out);

            var scaleX = textObj.width * 2 - textObj.width;
            var scaleY = textObj.height * 2 - textObj.height;
            this.tweenProperty(this.floatingTextGroup.position, "position", { x: this.floatingTextGroup.position.x - scaleX / 2, y: this.floatingTextGroup.position.y - scaleY / 2 }, 250, 0, Phaser.Easing.Back.Out);


            tweenObj.onComplete.addOnce(function () {
                var _tweenObj = this.tweenProperty(this, "alpha", {
                    alpha: 0
                }, 250, this._obj._timeToLive);

                _tweenObj.onComplete.addOnce(function () {
                    this.destroy();
                }, this);
            }, this.floatingTextGroup);
        } else if (type === "smoke") {
            startX = this.floatingTextGroup.x;
            startY = this.floatingTextGroup.y;
            side = this.state.rnd.integerInRange(0, 10);

            var pivot = 0;
            for (var j = 0; j < 12; j++) {
                if (pivot < 3 && pivot > 0) {
                    pointsX.push(startX + (10 * Math.abs(pivot)));
                    pointsY.push(startY - (this._obj._distance / 12 * j));
                    pivot += 1;
                } else if (pivot === 3) {
                    pivot = 0;
                    pointsX.push(startX - (10 * Math.abs(pivot)));
                    pointsY.push(startY - (this._obj._distance / 12 * j));
                    pivot -= 1;
                } else if (pivot === -3) {
                    pivot = 0;
                    pointsX.push(startX - (10 * Math.abs(pivot)));
                    pointsY.push(startY - (this._obj._distance / 12 * j));
                    pivot += 1;
                } else if (pivot > -3) {
                    pointsX.push(startX - (10 * Math.abs(pivot)));
                    pointsY.push(startY - (this._obj._distance / 12 * j));
                    pivot -= 1;
                }
            }

            tweenObj = this.state.add.tween(this.floatingTextGroup).to({
                x: pointsX,
                y: pointsY,
            }, this._obj.timeToLive, Phaser.Easing.Circular.Out, true).interpolation(function (v, k) {
                return Phaser.Math.bezierInterpolation(v, k);
            });

            tweenObj.onComplete.addOnce(function () {
                var _tweenObj = this.tweenProperty(this, "alpha", {
                    alpha: 0
                }, 250, 0);

                _tweenObj.onComplete.addOnce(function () {
                    this.destroy();
                }, this);
            }, this.floatingTextGroup);
        } else if (type === "up") {
            tweenObj = this.tweenProperty(this.floatingTextGroup, "y", {
                y: this.floatingTextGroup.y - this._obj._distance
            }, 400, 100, this._obj._easing);

            tweenObj.onComplete.addOnce(function () {
                var _tweenObj = this.tweenProperty(this.floatingTextGroup, "alpha", {
                    alpha: 0
                }, 150, this._obj._timeToLive);

                _tweenObj.onComplete.addOnce(function () {
                    this.floatingTextGroup.removeAll(true);
                    this.floatingTextGroup.destroy();
                }, this);
            }, this);
        } else if (type === "down") {
            tweenObj = this.tweenProperty(this.floatingTextGroup, "y", {
                y: this.floatingTextGroup.y + this._obj._distance
            }, 400, 100, this._obj._easing);

            tweenObj.onComplete.addOnce(function () {
                var _tweenObj = this.tweenProperty(this, "alpha", {
                    alpha: 0
                }, 250, this._obj._timeToLive);

                _tweenObj.onComplete.addOnce(function () {
                    this.floatingTextGroup.destroy();
                }, this);
            }, this);
        } else if (type === "left") {
            tweenObj = this.tweenProperty(this.floatingTextGroup, "y", {
                y: this.floatingTextGroup.x - this._obj._distance
            }, 400, 100, this._obj._easing);

            tweenObj.onComplete.addOnce(function () {
                var _tweenObj = this.tweenProperty(this, "alpha", {
                    alpha: 0
                }, 250, this._obj._timeToLive);

                _tweenObj.onComplete.addOnce(function () {
                    this.floatingTextGroup.destroy();
                }, this);
            }, this);
        } else if (type === "right") {
            tweenObj = this.tweenProperty(this.floatingTextGroup, "y", {
                y: this.floatingTextGroup + this._obj._distance
            }, 400, 100, this._obj._easing);

            tweenObj.onComplete.addOnce(function () {
                var _tweenObj = this.tweenProperty(this, "alpha", {
                    alpha: 0
                }, 250, this._obj._timeToLive);

                _tweenObj.onComplete.addOnce(function () {
                    this.floatingTextGroup.destroy();
                }, this);
            }, this);
        } else if (type === "fade") {
            this.floatingTextGroup.alpha = 0;
            tweenObj = this.tweenProperty(this.floatingTextGroup, "alpha", {
                alpha: 1
            }, 250, 50, this._obj._easing);

            tweenObj.onComplete.addOnce(function () {
                var _tweenObj = this.tweenProperty(this, "alpha", {
                    alpha: 0
                }, 350, this._obj._timeToLive);

                _tweenObj.onComplete.addOnce(function () {
                    this.floatingTextGroup.destroy();
                }, this);
            }, this);
        }
    }

    createPool() {
        var numOfItems = this.options.numOfItems || 50;
        this.mainFloatingTextPool = this.state.add.group();
        for (var i = 0; i < numOfItems; i++) {
            this.mainFloatingTextPool.add(createFloatingText(true));
        }
    }

    /**
     * [this.tweenProperty description]
     * @param  {[type]} item     [description]
     * @param  {[type]} property [description]
     * @param  {[type]} obj      [description]
     * @param  {[type]} duration [description]
     * @return {[type]}          [description]
     */
    tweenProperty(item, property, obj, duration, delay, easing, yoyo, repeat, reverse) {

        delay = delay || {};
        easing = easing || Phaser.Easing.Linear.None;
        yoyo = yoyo || false;
        repeat = repeat || 0;

        var tween = this.state.add.tween(item).to(obj, duration, easing, true, delay, repeat, yoyo);
        tween.reverse = reverse || false;
        return tween;
    }

};