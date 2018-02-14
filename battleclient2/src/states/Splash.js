import Phaser from 'phaser'
import { centerGameObjects } from '../utils'

export default class extends Phaser.State {
  init() { }

  preload() {
    this.loaderBg = this.add.sprite(this.game.world.centerX, this.game.world.centerY, 'loaderBg')
    this.loaderBar = this.add.sprite(this.game.world.centerX, this.game.world.centerY, 'loaderBar')
    centerGameObjects([this.loaderBg, this.loaderBar])

    this.load.setPreloadSprite(this.loaderBar)
    //
    // load your assets
    //
    this.load.image('bg', 'assets/images/background.jpg');

    this.load.atlas("wizard_ice", 'assets/wizard_ice/atlas.png', 'assets/wizard_ice/atlas.json')
    this.load.atlas("wizard_fire", 'assets/wizard_fire/atlas.png', 'assets/wizard_fire/atlas.json')
  }

  create() {
    this.state.start('Game')
  }
}
