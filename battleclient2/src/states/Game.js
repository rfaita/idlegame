/* globals __DEV__ */
import Phaser from 'phaser'
import Unit from '../sprites/Unit'

export default class extends Phaser.State {

  create() {

    this.SPEED = .05;

    this.timeBetweenAction = 1500 * this.SPEED;

    const xAB = (this.game.width / 2) - 150;
    const xDB = (this.game.width / 2) + 150;

    this.heroesPos = {};

    this.heroesPos["ATK_FRONT_1"] = { x: xAB + 30, y: 230 };
    this.heroesPos["ATK_FRONT_2"] = { x: xAB, y: 280 };

    this.heroesPos["ATK_BACK_1"] = { x: xAB - 100, y: 180 };
    this.heroesPos["ATK_BACK_2"] = { x: xAB - 130, y: 230 };
    this.heroesPos["ATK_BACK_3"] = { x: xAB - 160, y: 280 };
    this.heroesPos["ATK_BACK_4"] = { x: xAB - 190, y: 330 };

    this.heroesPos["DEF_FRONT_1"] = { x: xDB, y: 230 };
    this.heroesPos["DEF_FRONT_2"] = { x: xDB + 30, y: 280 };

    this.heroesPos["DEF_BACK_1"] = { x: xDB + 100, y: 180 };
    this.heroesPos["DEF_BACK_2"] = { x: xDB + 130, y: 230 };
    this.heroesPos["DEF_BACK_3"] = { x: xDB + 160, y: 280 };
    this.heroesPos["DEF_BACK_4"] = { x: xDB + 190, y: 330 };

    this.background = this.add.sprite(0, 0, 'bg');

    this.turnText = this.add.text(20, 20, '', { font: "40px Bangers", fill: "#FFFFFF", stroke: "#000000", strokeThickness: 2 });

    this.speedText = this.game.add.text(this.game.width - 50, 20, 1 / this.SPEED + "X", { font: "10px Bangers", fill: "#FFFFFF", stroke: "#000000", strokeThickness: 2 });

    this.heroes = {};

    for (var i = 0; i < this.data.attackFormation.heroes.length; i++) {
      var hp = this.data.attackFormation.heroes[i];
      var h = hp.hero;

      var hPos = this.heroesPos["ATK_" + hp.position];

      console.log(hPos);

      this.heroes["ATK_" + hp.position] = new Unit(this.game, {
        x: hPos.x, y: hPos.y, life: h.hp,
        scale: .3, asset: 'wizard_ice', timeBetweenAction: this.timeBetweenAction
      });

      this.game.add.existing(this.heroes["ATK_" + hp.position]);

    }

    for (var i = 0; i < this.data.defenseFormation.heroes.length; i++) {
      var hp = this.data.defenseFormation.heroes[i];
      var h = hp.hero;

      var hPos = this.heroesPos["DEF_" + hp.position];

      this.heroes["DEF_" + hp.position] = new Unit(this.game, {
        x: hPos.x, y: hPos.y, life: h.hp, teamType: 'DEF',
        scale: .3, asset: 'wizard_fire', timeBetweenAction: this.timeBetweenAction
      });

      this.game.add.existing(this.heroes["DEF_" + hp.position]);

    }

  }


  render() {

    this.game.debug.text(this.game.time.fps + "", 5, 14, '#00ff00');

    if (this.nextAction < this.timeBetweenAction + 200) {
      this.nextAction += this.game.time.elapsed
      return;
    }

    this.nextAction = 0;

    if (this.data == null) {
      return;
    }

    var event = this.data.battleLog[this.currAction++];

    //console.log(event;);

    if (event.battleEvent.actionType === "DMG" || event.battleEvent.actionType === "HEAL") {

      this.turnText.setText("Turno: " + event.turn);

      var prefixAction = event.heroOrigin.battleTeamType === "ATTACK_TEAM" ? "ATK_" : "DEF_";

      for (var i = 0; i < event.heroesTarget.length; i++) {

        var prefixTarget = event.heroesTarget[i].battleTeamType === "ATTACK_TEAM" ? "ATK_" : "DEF_";

        this.heroes[prefixAction + event.heroOrigin.position].action(this.heroes[prefixTarget + event.heroesTarget[i].position], event, event.heroesTarget[i]);

      }
    } else if (event.battleEvent.actionType === "BATTLE_END") {
      if (this.data.formationAttackWinner) {
        this.turnText.setText("Attack Win");
      } else {
        this.turnText.setText("Defense Win");
      }
      this.data = null;
    } else {
      this.nextAction = 999999999999999999999999999999999;
    }
  }

  constructor() {
    super();

    this.currAction = 0;
    this.nextAction = 0;


    this.data = {
      "battleLog": [
        {
          "uuid": "8b7a122e-993c-4305-bc0b-834fc9e43571",
          "turn": 1,
          "battleEvent": {
            "actionType": "BATTLE_START"
          }
        },
        {
          "uuid": "e0df7d13-03e0-42f8-a3a6-42e55fa347f3",
          "turn": 1,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "ATTACK_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 30389,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -2949,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 60,
              "battleTeamType": "DEFENSE_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 15131,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "5782abab-5197-477e-b10c-8b9556297ed4",
          "turn": 1,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 30389,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -2949,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 60,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 15131,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "9bede6ed-5cdb-407f-b4ec-6817f7a3eafc",
          "turn": 1,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "ATTACK_TEAM",
            "position": "FRONT_2",
            "hero": {
              "id": "5a82dfa1eeec75329d876020",
              "heroType": {
                "quality": "AVERAGE",
                "faction": "SHADOW",
                "role": "PRIEST",
                "name": "Kharma",
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 4727,
              "armor": 617,
              "magicResist": 626,
              "speed": 604,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 18080,
              "currDmg": 4727,
              "currArmor": 617,
              "currMagicResist": 626,
              "currSpeed": 604,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 15131,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -4286,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 70,
              "battleTeamType": "DEFENSE_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 10845,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "672fb18d-e1c1-4460-87e9-ecd6df099fce",
          "turn": 1,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_2",
            "hero": {
              "id": "5a82dfa1eeec75329d876020",
              "heroType": {
                "quality": "AVERAGE",
                "faction": "SHADOW",
                "role": "PRIEST",
                "name": "Kharma",
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 4727,
              "armor": 617,
              "magicResist": 626,
              "speed": 604,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 18080,
              "currDmg": 4727,
              "currArmor": 617,
              "currMagicResist": 626,
              "currSpeed": 604,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 10845,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -4286,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 10845,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "62cf8e91-c772-4776-b9d5-ac5caaf32a08",
          "turn": 2,
          "heroOrigin": {
            "energy": 0,
            "battleTeamType": "ATTACK_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 30389,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -4582,
            "damageType": "MAGIC"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "DEFENSE_TEAM",
              "position": "FRONT_1",
              "hero": {
                "id": "5a82df95eeec75329d87601f",
                "heroType": {
                  "quality": "POOR",
                  "faction": "LIGHT",
                  "role": "PRIEST",
                  "name": "Gerke",
                  "specialAction": {
                    "special": true,
                    "mainActionEffect": {
                      "type": "DMG",
                      "targetType": "RANDOM",
                      "canBeDodge": true,
                      "percentage": 158,
                      "damageType": "MAGIC",
                      "overSameTeam": false
                    },
                    "secundaryActionsEffects": [
                      {
                        "type": "HEAL",
                        "targetType": "LESS_PERC_LIFE",
                        "canBeDodge": true,
                        "percentage": 420,
                        "damageType": "MAGIC",
                        "overSameTeam": true
                      }
                    ]
                  },
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 3253,
                "armor": 730,
                "magicResist": 730,
                "speed": 624,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 30389,
                "currDmg": 3253,
                "currArmor": 730,
                "currMagicResist": 730,
                "currSpeed": 624,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 25807,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "c18751bb-c88c-409b-a996-3a772b7eecce",
          "turn": 2,
          "heroOrigin": {
            "energy": 0,
            "battleTeamType": "ATTACK_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 30389,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "HEAL",
            "value": 13662,
            "damageType": "MAGIC"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 18080,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "ec4173d3-4228-4c02-bfe2-6b3828a1ca95",
          "turn": 2,
          "heroOrigin": {
            "energy": 0,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 25807,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -4654,
            "damageType": "MAGIC"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 13426,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "9755d507-bb1f-4ea9-8c6e-0bfd7bdeaf38",
          "turn": 2,
          "heroOrigin": {
            "energy": 0,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 25807,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "HEAL",
            "value": 13662,
            "damageType": "MAGIC"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "DEFENSE_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 18080,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "91daa2ff-4bc2-4031-9df3-a280c1b842bf",
          "turn": 2,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "ATTACK_TEAM",
            "position": "FRONT_2",
            "hero": {
              "id": "5a82dfa1eeec75329d876020",
              "heroType": {
                "quality": "AVERAGE",
                "faction": "SHADOW",
                "role": "PRIEST",
                "name": "Kharma",
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 4727,
              "armor": 617,
              "magicResist": 626,
              "speed": 604,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 18080,
              "currDmg": 4727,
              "currArmor": 617,
              "currMagicResist": 626,
              "currSpeed": 604,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 13426,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -4286,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "DEFENSE_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 13794,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "21c1a871-69bc-4823-82e4-6da61ed36585",
          "turn": 2,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_2",
            "hero": {
              "id": "5a82dfa1eeec75329d876020",
              "heroType": {
                "quality": "AVERAGE",
                "faction": "SHADOW",
                "role": "PRIEST",
                "name": "Kharma",
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 4727,
              "armor": 617,
              "magicResist": 626,
              "speed": 604,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 18080,
              "currDmg": 4727,
              "currArmor": 617,
              "currMagicResist": 626,
              "currSpeed": 604,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 13794,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -4286,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 9140,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "80139495-c412-40e8-9755-137727eed08e",
          "turn": 3,
          "heroOrigin": {
            "energy": 50,
            "battleTeamType": "ATTACK_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 30389,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -2949,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "DEFENSE_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 10845,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "25647b4d-d333-43e7-ace3-21d5c6e8a535",
          "turn": 3,
          "heroOrigin": {
            "energy": 50,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 25807,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -2949,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 6191,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "915d1762-59b0-4dd1-b391-72b6d601fa19",
          "turn": 3,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "ATTACK_TEAM",
            "position": "FRONT_2",
            "hero": {
              "id": "5a82dfa1eeec75329d876020",
              "heroType": {
                "quality": "AVERAGE",
                "faction": "SHADOW",
                "role": "PRIEST",
                "name": "Kharma",
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 4727,
              "armor": 617,
              "magicResist": 626,
              "speed": 604,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 18080,
              "currDmg": 4727,
              "currArmor": 617,
              "currMagicResist": 626,
              "currSpeed": 604,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 6191,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -4286,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "DEFENSE_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 6559,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "e5ca15fd-9801-4552-bf89-db7e07dfe8ca",
          "turn": 3,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_2",
            "hero": {
              "id": "5a82dfa1eeec75329d876020",
              "heroType": {
                "quality": "AVERAGE",
                "faction": "SHADOW",
                "role": "PRIEST",
                "name": "Kharma",
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 4727,
              "armor": 617,
              "magicResist": 626,
              "speed": 604,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 18080,
              "currDmg": 4727,
              "currArmor": 617,
              "currMagicResist": 626,
              "currSpeed": 604,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 6559,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -4286,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 1905,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "c4107cbe-667a-443b-916a-fe9a4cbf02c8",
          "turn": 4,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "ATTACK_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 30389,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -2949,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "DEFENSE_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 3610,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "874b02f8-554d-49de-8f66-299d82a89f45",
          "turn": 4,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 25807,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -2949,
            "subType": "DEATH",
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 0,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 0,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "4a610305-0ccf-4450-a8ab-9199edd0480c",
          "turn": 4,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_2",
            "hero": {
              "id": "5a82dfa1eeec75329d876020",
              "heroType": {
                "quality": "AVERAGE",
                "faction": "SHADOW",
                "role": "PRIEST",
                "name": "Kharma",
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 4727,
              "armor": 617,
              "magicResist": 626,
              "speed": 604,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 18080,
              "currDmg": 4727,
              "currArmor": 617,
              "currMagicResist": 626,
              "currSpeed": 604,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 3610,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -4214,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_1",
              "hero": {
                "id": "5a82df95eeec75329d87601f",
                "heroType": {
                  "quality": "POOR",
                  "faction": "LIGHT",
                  "role": "PRIEST",
                  "name": "Gerke",
                  "specialAction": {
                    "special": true,
                    "mainActionEffect": {
                      "type": "DMG",
                      "targetType": "RANDOM",
                      "canBeDodge": true,
                      "percentage": 158,
                      "damageType": "MAGIC",
                      "overSameTeam": false
                    },
                    "secundaryActionsEffects": [
                      {
                        "type": "HEAL",
                        "targetType": "LESS_PERC_LIFE",
                        "canBeDodge": true,
                        "percentage": 420,
                        "damageType": "MAGIC",
                        "overSameTeam": true
                      }
                    ]
                  },
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 3253,
                "armor": 730,
                "magicResist": 730,
                "speed": 624,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 30389,
                "currDmg": 3253,
                "currArmor": 730,
                "currMagicResist": 730,
                "currSpeed": 624,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 26175,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "e6130629-cc2a-4e3b-91e1-078f8f56e9be",
          "turn": 5,
          "heroOrigin": {
            "energy": 0,
            "battleTeamType": "ATTACK_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 26175,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -4582,
            "damageType": "MAGIC"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "DEFENSE_TEAM",
              "position": "FRONT_1",
              "hero": {
                "id": "5a82df95eeec75329d87601f",
                "heroType": {
                  "quality": "POOR",
                  "faction": "LIGHT",
                  "role": "PRIEST",
                  "name": "Gerke",
                  "specialAction": {
                    "special": true,
                    "mainActionEffect": {
                      "type": "DMG",
                      "targetType": "RANDOM",
                      "canBeDodge": true,
                      "percentage": 158,
                      "damageType": "MAGIC",
                      "overSameTeam": false
                    },
                    "secundaryActionsEffects": [
                      {
                        "type": "HEAL",
                        "targetType": "LESS_PERC_LIFE",
                        "canBeDodge": true,
                        "percentage": 420,
                        "damageType": "MAGIC",
                        "overSameTeam": true
                      }
                    ]
                  },
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 3253,
                "armor": 730,
                "magicResist": 730,
                "speed": 624,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 30389,
                "currDmg": 3253,
                "currArmor": 730,
                "currMagicResist": 730,
                "currSpeed": 624,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 21225,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "6262e386-006e-424d-b744-efe2cb01353b",
          "turn": 5,
          "heroOrigin": {
            "energy": 0,
            "battleTeamType": "ATTACK_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 30389,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "HEAL",
            "value": 13662,
            "damageType": "MAGIC"
          },
          "heroesTarget": [
            {
              "energy": 0,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_1",
              "hero": {
                "id": "5a82df95eeec75329d87601f",
                "heroType": {
                  "quality": "POOR",
                  "faction": "LIGHT",
                  "role": "PRIEST",
                  "name": "Gerke",
                  "specialAction": {
                    "special": true,
                    "mainActionEffect": {
                      "type": "DMG",
                      "targetType": "RANDOM",
                      "canBeDodge": true,
                      "percentage": 158,
                      "damageType": "MAGIC",
                      "overSameTeam": false
                    },
                    "secundaryActionsEffects": [
                      {
                        "type": "HEAL",
                        "targetType": "LESS_PERC_LIFE",
                        "canBeDodge": true,
                        "percentage": 420,
                        "damageType": "MAGIC",
                        "overSameTeam": true
                      }
                    ]
                  },
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 3253,
                "armor": 730,
                "magicResist": 730,
                "speed": 624,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 30389,
                "currDmg": 3253,
                "currArmor": 730,
                "currMagicResist": 730,
                "currSpeed": 624,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 30389,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "ab9b1773-b503-4b40-b279-c3615455d2a9",
          "turn": 5,
          "heroOrigin": {
            "energy": 0,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 21225,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -4582,
            "damageType": "MAGIC"
          },
          "heroesTarget": [
            {
              "energy": 10,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_1",
              "hero": {
                "id": "5a82df95eeec75329d87601f",
                "heroType": {
                  "quality": "POOR",
                  "faction": "LIGHT",
                  "role": "PRIEST",
                  "name": "Gerke",
                  "specialAction": {
                    "special": true,
                    "mainActionEffect": {
                      "type": "DMG",
                      "targetType": "RANDOM",
                      "canBeDodge": true,
                      "percentage": 158,
                      "damageType": "MAGIC",
                      "overSameTeam": false
                    },
                    "secundaryActionsEffects": [
                      {
                        "type": "HEAL",
                        "targetType": "LESS_PERC_LIFE",
                        "canBeDodge": true,
                        "percentage": 420,
                        "damageType": "MAGIC",
                        "overSameTeam": true
                      }
                    ]
                  },
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 3253,
                "armor": 730,
                "magicResist": 730,
                "speed": 624,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 30389,
                "currDmg": 3253,
                "currArmor": 730,
                "currMagicResist": 730,
                "currSpeed": 624,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 25807,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "6f093d53-9225-4792-97cf-3b5d2d6fc594",
          "turn": 5,
          "heroOrigin": {
            "energy": 0,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 21225,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "HEAL",
            "value": 13662,
            "damageType": "MAGIC"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "DEFENSE_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 17272,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "bd5939ae-8f1d-45fd-8e3f-49d0c68c49e8",
          "turn": 5,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_2",
            "hero": {
              "id": "5a82dfa1eeec75329d876020",
              "heroType": {
                "quality": "AVERAGE",
                "faction": "SHADOW",
                "role": "PRIEST",
                "name": "Kharma",
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 4727,
              "armor": 617,
              "magicResist": 626,
              "speed": 604,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 18080,
              "currDmg": 4727,
              "currArmor": 617,
              "currMagicResist": 626,
              "currSpeed": 604,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 17272,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -4214,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 20,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_1",
              "hero": {
                "id": "5a82df95eeec75329d87601f",
                "heroType": {
                  "quality": "POOR",
                  "faction": "LIGHT",
                  "role": "PRIEST",
                  "name": "Gerke",
                  "specialAction": {
                    "special": true,
                    "mainActionEffect": {
                      "type": "DMG",
                      "targetType": "RANDOM",
                      "canBeDodge": true,
                      "percentage": 158,
                      "damageType": "MAGIC",
                      "overSameTeam": false
                    },
                    "secundaryActionsEffects": [
                      {
                        "type": "HEAL",
                        "targetType": "LESS_PERC_LIFE",
                        "canBeDodge": true,
                        "percentage": 420,
                        "damageType": "MAGIC",
                        "overSameTeam": true
                      }
                    ]
                  },
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 3253,
                "armor": 730,
                "magicResist": 730,
                "speed": 624,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 30389,
                "currDmg": 3253,
                "currArmor": 730,
                "currMagicResist": 730,
                "currSpeed": 624,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 21593,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "4b5d1438-2315-46f8-9bf8-53eb2507d725",
          "turn": 6,
          "heroOrigin": {
            "energy": 70,
            "battleTeamType": "ATTACK_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 21593,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -2949,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "DEFENSE_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 14323,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "9dbeee29-606f-4ba4-b413-7ce527a3e7a0",
          "turn": 6,
          "heroOrigin": {
            "energy": 50,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 21225,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -2900,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 80,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_1",
              "hero": {
                "id": "5a82df95eeec75329d87601f",
                "heroType": {
                  "quality": "POOR",
                  "faction": "LIGHT",
                  "role": "PRIEST",
                  "name": "Gerke",
                  "specialAction": {
                    "special": true,
                    "mainActionEffect": {
                      "type": "DMG",
                      "targetType": "RANDOM",
                      "canBeDodge": true,
                      "percentage": 158,
                      "damageType": "MAGIC",
                      "overSameTeam": false
                    },
                    "secundaryActionsEffects": [
                      {
                        "type": "HEAL",
                        "targetType": "LESS_PERC_LIFE",
                        "canBeDodge": true,
                        "percentage": 420,
                        "damageType": "MAGIC",
                        "overSameTeam": true
                      }
                    ]
                  },
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 3253,
                "armor": 730,
                "magicResist": 730,
                "speed": 624,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 30389,
                "currDmg": 3253,
                "currArmor": 730,
                "currMagicResist": 730,
                "currSpeed": 624,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 18693,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "7ba4627d-2f25-40dc-8ac5-e7c17b824a12",
          "turn": 6,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_2",
            "hero": {
              "id": "5a82dfa1eeec75329d876020",
              "heroType": {
                "quality": "AVERAGE",
                "faction": "SHADOW",
                "role": "PRIEST",
                "name": "Kharma",
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 4727,
              "armor": 617,
              "magicResist": 626,
              "speed": 604,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 18080,
              "currDmg": 4727,
              "currArmor": 617,
              "currMagicResist": 626,
              "currSpeed": 604,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 14323,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -4214,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 90,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_1",
              "hero": {
                "id": "5a82df95eeec75329d87601f",
                "heroType": {
                  "quality": "POOR",
                  "faction": "LIGHT",
                  "role": "PRIEST",
                  "name": "Gerke",
                  "specialAction": {
                    "special": true,
                    "mainActionEffect": {
                      "type": "DMG",
                      "targetType": "RANDOM",
                      "canBeDodge": true,
                      "percentage": 158,
                      "damageType": "MAGIC",
                      "overSameTeam": false
                    },
                    "secundaryActionsEffects": [
                      {
                        "type": "HEAL",
                        "targetType": "LESS_PERC_LIFE",
                        "canBeDodge": true,
                        "percentage": 420,
                        "damageType": "MAGIC",
                        "overSameTeam": true
                      }
                    ]
                  },
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 3253,
                "armor": 730,
                "magicResist": 730,
                "speed": 624,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 30389,
                "currDmg": 3253,
                "currArmor": 730,
                "currMagicResist": 730,
                "currSpeed": 624,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 14479,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "78abb02e-aa05-4724-859e-8716dfc931e0",
          "turn": 7,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "ATTACK_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 14479,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -2949,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "DEFENSE_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 11374,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "24326e30-33da-4387-84dd-391e0acaf6f8",
          "turn": 7,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 21225,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -2900,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_1",
              "hero": {
                "id": "5a82df95eeec75329d87601f",
                "heroType": {
                  "quality": "POOR",
                  "faction": "LIGHT",
                  "role": "PRIEST",
                  "name": "Gerke",
                  "specialAction": {
                    "special": true,
                    "mainActionEffect": {
                      "type": "DMG",
                      "targetType": "RANDOM",
                      "canBeDodge": true,
                      "percentage": 158,
                      "damageType": "MAGIC",
                      "overSameTeam": false
                    },
                    "secundaryActionsEffects": [
                      {
                        "type": "HEAL",
                        "targetType": "LESS_PERC_LIFE",
                        "canBeDodge": true,
                        "percentage": 420,
                        "damageType": "MAGIC",
                        "overSameTeam": true
                      }
                    ]
                  },
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 3253,
                "armor": 730,
                "magicResist": 730,
                "speed": 624,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 30389,
                "currDmg": 3253,
                "currArmor": 730,
                "currMagicResist": 730,
                "currSpeed": 624,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 11579,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "c396274e-8f51-4d29-8b52-d367d83e1fb2",
          "turn": 7,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_2",
            "hero": {
              "id": "5a82dfa1eeec75329d876020",
              "heroType": {
                "quality": "AVERAGE",
                "faction": "SHADOW",
                "role": "PRIEST",
                "name": "Kharma",
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 4727,
              "armor": 617,
              "magicResist": 626,
              "speed": 604,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 18080,
              "currDmg": 4727,
              "currArmor": 617,
              "currMagicResist": 626,
              "currSpeed": 604,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 11374,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -4214,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_1",
              "hero": {
                "id": "5a82df95eeec75329d87601f",
                "heroType": {
                  "quality": "POOR",
                  "faction": "LIGHT",
                  "role": "PRIEST",
                  "name": "Gerke",
                  "specialAction": {
                    "special": true,
                    "mainActionEffect": {
                      "type": "DMG",
                      "targetType": "RANDOM",
                      "canBeDodge": true,
                      "percentage": 158,
                      "damageType": "MAGIC",
                      "overSameTeam": false
                    },
                    "secundaryActionsEffects": [
                      {
                        "type": "HEAL",
                        "targetType": "LESS_PERC_LIFE",
                        "canBeDodge": true,
                        "percentage": 420,
                        "damageType": "MAGIC",
                        "overSameTeam": true
                      }
                    ]
                  },
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 3253,
                "armor": 730,
                "magicResist": 730,
                "speed": 624,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 30389,
                "currDmg": 3253,
                "currArmor": 730,
                "currMagicResist": 730,
                "currSpeed": 624,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 7365,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "647f9838-bcb4-4099-abfd-94df6bf10a1b",
          "turn": 8,
          "heroOrigin": {
            "energy": 0,
            "battleTeamType": "ATTACK_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 7365,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -4654,
            "damageType": "MAGIC"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "DEFENSE_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 6720,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "526d7877-9462-484c-94de-c42757ae0fe6",
          "turn": 8,
          "heroOrigin": {
            "energy": 0,
            "battleTeamType": "ATTACK_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 21027,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "HEAL",
            "value": 13662,
            "damageType": "MAGIC"
          },
          "heroesTarget": [
            {
              "energy": 0,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_1",
              "hero": {
                "id": "5a82df95eeec75329d87601f",
                "heroType": {
                  "quality": "POOR",
                  "faction": "LIGHT",
                  "role": "PRIEST",
                  "name": "Gerke",
                  "specialAction": {
                    "special": true,
                    "mainActionEffect": {
                      "type": "DMG",
                      "targetType": "RANDOM",
                      "canBeDodge": true,
                      "percentage": 158,
                      "damageType": "MAGIC",
                      "overSameTeam": false
                    },
                    "secundaryActionsEffects": [
                      {
                        "type": "HEAL",
                        "targetType": "LESS_PERC_LIFE",
                        "canBeDodge": true,
                        "percentage": 420,
                        "damageType": "MAGIC",
                        "overSameTeam": true
                      }
                    ]
                  },
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 3253,
                "armor": 730,
                "magicResist": 730,
                "speed": 624,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 30389,
                "currDmg": 3253,
                "currArmor": 730,
                "currMagicResist": 730,
                "currSpeed": 624,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 21027,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "67aa905a-b180-4599-a0cf-a6f966f5999f",
          "turn": 8,
          "heroOrigin": {
            "energy": 0,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 21225,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -4582,
            "damageType": "MAGIC"
          },
          "heroesTarget": [
            {
              "energy": 10,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_1",
              "hero": {
                "id": "5a82df95eeec75329d87601f",
                "heroType": {
                  "quality": "POOR",
                  "faction": "LIGHT",
                  "role": "PRIEST",
                  "name": "Gerke",
                  "specialAction": {
                    "special": true,
                    "mainActionEffect": {
                      "type": "DMG",
                      "targetType": "RANDOM",
                      "canBeDodge": true,
                      "percentage": 158,
                      "damageType": "MAGIC",
                      "overSameTeam": false
                    },
                    "secundaryActionsEffects": [
                      {
                        "type": "HEAL",
                        "targetType": "LESS_PERC_LIFE",
                        "canBeDodge": true,
                        "percentage": 420,
                        "damageType": "MAGIC",
                        "overSameTeam": true
                      }
                    ]
                  },
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 3253,
                "armor": 730,
                "magicResist": 730,
                "speed": 624,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 30389,
                "currDmg": 3253,
                "currArmor": 730,
                "currMagicResist": 730,
                "currSpeed": 624,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 16445,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "53a5768f-3181-4822-a75d-d8f929c7a53c",
          "turn": 8,
          "heroOrigin": {
            "energy": 0,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 21225,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "HEAL",
            "value": 13662,
            "damageType": "MAGIC"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "DEFENSE_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 18080,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "ad2920d9-4ad3-4926-a134-2bbb926d3f18",
          "turn": 8,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_2",
            "hero": {
              "id": "5a82dfa1eeec75329d876020",
              "heroType": {
                "quality": "AVERAGE",
                "faction": "SHADOW",
                "role": "PRIEST",
                "name": "Kharma",
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 4727,
              "armor": 617,
              "magicResist": 626,
              "speed": 604,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 18080,
              "currDmg": 4727,
              "currArmor": 617,
              "currMagicResist": 626,
              "currSpeed": 604,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 18080,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -4214,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 20,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_1",
              "hero": {
                "id": "5a82df95eeec75329d87601f",
                "heroType": {
                  "quality": "POOR",
                  "faction": "LIGHT",
                  "role": "PRIEST",
                  "name": "Gerke",
                  "specialAction": {
                    "special": true,
                    "mainActionEffect": {
                      "type": "DMG",
                      "targetType": "RANDOM",
                      "canBeDodge": true,
                      "percentage": 158,
                      "damageType": "MAGIC",
                      "overSameTeam": false
                    },
                    "secundaryActionsEffects": [
                      {
                        "type": "HEAL",
                        "targetType": "LESS_PERC_LIFE",
                        "canBeDodge": true,
                        "percentage": 420,
                        "damageType": "MAGIC",
                        "overSameTeam": true
                      }
                    ]
                  },
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 3253,
                "armor": 730,
                "magicResist": 730,
                "speed": 624,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 30389,
                "currDmg": 3253,
                "currArmor": 730,
                "currMagicResist": 730,
                "currSpeed": 624,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 12231,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "51d22999-7ad2-40cc-89b4-827f04c21f9a",
          "turn": 9,
          "heroOrigin": {
            "energy": 70,
            "battleTeamType": "ATTACK_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 12231,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -2949,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "DEFENSE_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 15131,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "6f820313-eccf-4d11-a6d4-df994f496a26",
          "turn": 9,
          "heroOrigin": {
            "energy": 50,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 21225,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -2900,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 80,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_1",
              "hero": {
                "id": "5a82df95eeec75329d87601f",
                "heroType": {
                  "quality": "POOR",
                  "faction": "LIGHT",
                  "role": "PRIEST",
                  "name": "Gerke",
                  "specialAction": {
                    "special": true,
                    "mainActionEffect": {
                      "type": "DMG",
                      "targetType": "RANDOM",
                      "canBeDodge": true,
                      "percentage": 158,
                      "damageType": "MAGIC",
                      "overSameTeam": false
                    },
                    "secundaryActionsEffects": [
                      {
                        "type": "HEAL",
                        "targetType": "LESS_PERC_LIFE",
                        "canBeDodge": true,
                        "percentage": 420,
                        "damageType": "MAGIC",
                        "overSameTeam": true
                      }
                    ]
                  },
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 3253,
                "armor": 730,
                "magicResist": 730,
                "speed": 624,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 30389,
                "currDmg": 3253,
                "currArmor": 730,
                "currMagicResist": 730,
                "currSpeed": 624,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 9331,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "bf47e2c9-7728-40a4-92d4-bdd5e0dc9c45",
          "turn": 9,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_2",
            "hero": {
              "id": "5a82dfa1eeec75329d876020",
              "heroType": {
                "quality": "AVERAGE",
                "faction": "SHADOW",
                "role": "PRIEST",
                "name": "Kharma",
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 4727,
              "armor": 617,
              "magicResist": 626,
              "speed": 604,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 18080,
              "currDmg": 4727,
              "currArmor": 617,
              "currMagicResist": 626,
              "currSpeed": 604,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 15131,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -4214,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 90,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_1",
              "hero": {
                "id": "5a82df95eeec75329d87601f",
                "heroType": {
                  "quality": "POOR",
                  "faction": "LIGHT",
                  "role": "PRIEST",
                  "name": "Gerke",
                  "specialAction": {
                    "special": true,
                    "mainActionEffect": {
                      "type": "DMG",
                      "targetType": "RANDOM",
                      "canBeDodge": true,
                      "percentage": 158,
                      "damageType": "MAGIC",
                      "overSameTeam": false
                    },
                    "secundaryActionsEffects": [
                      {
                        "type": "HEAL",
                        "targetType": "LESS_PERC_LIFE",
                        "canBeDodge": true,
                        "percentage": 420,
                        "damageType": "MAGIC",
                        "overSameTeam": true
                      }
                    ]
                  },
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 3253,
                "armor": 730,
                "magicResist": 730,
                "speed": 624,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 30389,
                "currDmg": 3253,
                "currArmor": 730,
                "currMagicResist": 730,
                "currSpeed": 624,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 5117,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "305df361-7642-4999-b519-fcacecb09594",
          "turn": 10,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "ATTACK_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 5117,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -2949,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "DEFENSE_TEAM",
              "position": "FRONT_2",
              "hero": {
                "id": "5a82dfa1eeec75329d876020",
                "heroType": {
                  "quality": "AVERAGE",
                  "faction": "SHADOW",
                  "role": "PRIEST",
                  "name": "Kharma",
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 4727,
                "armor": 617,
                "magicResist": 626,
                "speed": 604,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 18080,
                "currDmg": 4727,
                "currArmor": 617,
                "currMagicResist": 626,
                "currSpeed": 604,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 12182,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "a82679ee-c836-4c97-a115-9ad8da38585c",
          "turn": 10,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 21225,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -2900,
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 100,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_1",
              "hero": {
                "id": "5a82df95eeec75329d87601f",
                "heroType": {
                  "quality": "POOR",
                  "faction": "LIGHT",
                  "role": "PRIEST",
                  "name": "Gerke",
                  "specialAction": {
                    "special": true,
                    "mainActionEffect": {
                      "type": "DMG",
                      "targetType": "RANDOM",
                      "canBeDodge": true,
                      "percentage": 158,
                      "damageType": "MAGIC",
                      "overSameTeam": false
                    },
                    "secundaryActionsEffects": [
                      {
                        "type": "HEAL",
                        "targetType": "LESS_PERC_LIFE",
                        "canBeDodge": true,
                        "percentage": 420,
                        "damageType": "MAGIC",
                        "overSameTeam": true
                      }
                    ]
                  },
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 3253,
                "armor": 730,
                "magicResist": 730,
                "speed": 624,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 30389,
                "currDmg": 3253,
                "currArmor": 730,
                "currMagicResist": 730,
                "currSpeed": 624,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 2217,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "9af8e4da-4449-4c05-aa8c-bd40c41506ac",
          "turn": 10,
          "heroOrigin": {
            "energy": 100,
            "battleTeamType": "DEFENSE_TEAM",
            "position": "FRONT_2",
            "hero": {
              "id": "5a82dfa1eeec75329d876020",
              "heroType": {
                "quality": "AVERAGE",
                "faction": "SHADOW",
                "role": "PRIEST",
                "name": "Kharma",
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 4727,
              "armor": 617,
              "magicResist": 626,
              "speed": 604,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 18080,
              "currDmg": 4727,
              "currArmor": 617,
              "currMagicResist": 626,
              "currSpeed": 604,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 12182,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          "battleEvent": {
            "actionType": "DMG",
            "value": -4214,
            "subType": "DEATH",
            "damageType": "PHYSICAL"
          },
          "heroesTarget": [
            {
              "energy": 0,
              "battleTeamType": "ATTACK_TEAM",
              "position": "FRONT_1",
              "hero": {
                "id": "5a82df95eeec75329d87601f",
                "heroType": {
                  "quality": "POOR",
                  "faction": "LIGHT",
                  "role": "PRIEST",
                  "name": "Gerke",
                  "specialAction": {
                    "special": true,
                    "mainActionEffect": {
                      "type": "DMG",
                      "targetType": "RANDOM",
                      "canBeDodge": true,
                      "percentage": 158,
                      "damageType": "MAGIC",
                      "overSameTeam": false
                    },
                    "secundaryActionsEffects": [
                      {
                        "type": "HEAL",
                        "targetType": "LESS_PERC_LIFE",
                        "canBeDodge": true,
                        "percentage": 420,
                        "damageType": "MAGIC",
                        "overSameTeam": true
                      }
                    ]
                  },
                  "damageType": "MAGIC",
                  "distanceType": "RANGED",
                  "maxLevel": 250
                },
                "level": 1,
                "dmg": 3253,
                "armor": 730,
                "magicResist": 730,
                "speed": 624,
                "luck": 0,
                "critChance": 0,
                "critDamage": 0,
                "dodgeChance": 0,
                "hp": 30389,
                "currDmg": 3253,
                "currArmor": 730,
                "currMagicResist": 730,
                "currSpeed": 624,
                "currLuck": 0,
                "currCritChance": 0,
                "currCritDamage": 0,
                "currDodgeChance": 0,
                "currHp": 0,
                "canDoAction": true,
                "canDoSpecialAction": true,
                "currBuffs": []
              }
            }
          ]
        },
        {
          "uuid": "21401030-b81c-40d6-b5d0-fb9fab5e3886",
          "turn": 10,
          "battleEvent": {
            "actionType": "BATTLE_END"
          }
        }
      ],
      "turn": 10,
      "attackFormation": {
        "formationType": "ATTACK",
        "size": 2,
        "heroes": [
          {
            "energy": 0,
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 0,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          {
            "energy": 0,
            "position": "FRONT_2",
            "hero": {
              "id": "5a82dfa1eeec75329d876020",
              "heroType": {
                "quality": "AVERAGE",
                "faction": "SHADOW",
                "role": "PRIEST",
                "name": "Kharma",
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 4727,
              "armor": 617,
              "magicResist": 626,
              "speed": 604,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 18080,
              "currDmg": 4727,
              "currArmor": 617,
              "currMagicResist": 626,
              "currSpeed": 604,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 0,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          }
        ]
      },
      "defenseFormation": {
        "formationType": "DEFENSE",
        "size": 2,
        "heroes": [
          {
            "energy": 0,
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 21225,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          {
            "energy": 0,
            "position": "FRONT_2",
            "hero": {
              "id": "5a82dfa1eeec75329d876020",
              "heroType": {
                "quality": "AVERAGE",
                "faction": "SHADOW",
                "role": "PRIEST",
                "name": "Kharma",
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 4727,
              "armor": 617,
              "magicResist": 626,
              "speed": 604,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 18080,
              "currDmg": 4727,
              "currArmor": 617,
              "currMagicResist": 626,
              "currSpeed": 604,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 12182,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          }
        ]
      },
      "winner": {
        "formationType": "DEFENSE",
        "size": 2,
        "heroes": [
          {
            "energy": 0,
            "position": "FRONT_1",
            "hero": {
              "id": "5a82df95eeec75329d87601f",
              "heroType": {
                "quality": "POOR",
                "faction": "LIGHT",
                "role": "PRIEST",
                "name": "Gerke",
                "specialAction": {
                  "special": true,
                  "mainActionEffect": {
                    "type": "DMG",
                    "targetType": "RANDOM",
                    "canBeDodge": true,
                    "percentage": 158,
                    "damageType": "MAGIC",
                    "overSameTeam": false
                  },
                  "secundaryActionsEffects": [
                    {
                      "type": "HEAL",
                      "targetType": "LESS_PERC_LIFE",
                      "canBeDodge": true,
                      "percentage": 420,
                      "damageType": "MAGIC",
                      "overSameTeam": true
                    }
                  ]
                },
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 3253,
              "armor": 730,
              "magicResist": 730,
              "speed": 624,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 30389,
              "currDmg": 3253,
              "currArmor": 730,
              "currMagicResist": 730,
              "currSpeed": 624,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 21225,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          },
          {
            "energy": 0,
            "position": "FRONT_2",
            "hero": {
              "id": "5a82dfa1eeec75329d876020",
              "heroType": {
                "quality": "AVERAGE",
                "faction": "SHADOW",
                "role": "PRIEST",
                "name": "Kharma",
                "damageType": "MAGIC",
                "distanceType": "RANGED",
                "maxLevel": 250
              },
              "level": 1,
              "dmg": 4727,
              "armor": 617,
              "magicResist": 626,
              "speed": 604,
              "luck": 0,
              "critChance": 0,
              "critDamage": 0,
              "dodgeChance": 0,
              "hp": 18080,
              "currDmg": 4727,
              "currArmor": 617,
              "currMagicResist": 626,
              "currSpeed": 604,
              "currLuck": 0,
              "currCritChance": 0,
              "currCritDamage": 0,
              "currDodgeChance": 0,
              "currHp": 12182,
              "canDoAction": true,
              "canDoSpecialAction": true,
              "currBuffs": []
            }
          }
        ]
      },
      "formationAttackWinner": false
    }
  }


}
