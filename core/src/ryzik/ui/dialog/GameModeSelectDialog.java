package ryzik.ui.dialog;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import ryzik.Vars;
import ryzik.content.Events;
import ryzik.content.Items;
import ryzik.content.MobTypes;
import ryzik.content.Teams;
import ryzik.screen.EatDefenseGameScreen;
import ryzik.screen.GameScreen;
import ryzik.type.item.Inventory;
import ryzik.type.world.Map;
import ryzik.type.world.mob.Player;
import ryzik.type.world.mob.Weapon;
import ryzik.ui.BackgroundActor;

public class GameModeSelectDialog extends Dialog {
    private Table table;
    private Map map;

    public GameModeSelectDialog(final Map map) {
        this.map = map;

        table = new Table();

        TextButton eatDefenceStartButton = new TextButton("eat defence", Vars.skin);
        eatDefenceStartButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen gameScreen = new EatDefenseGameScreen();
                gameScreen.world = map.createWorld();

                Player mob = new Player(MobTypes.ryzik);
                mob.team = Teams.orange;
                map.addMob(mob);
                mob.position.set(35, 23);
                mob.inventory = new Inventory(5,5);
                mob.currentWeapon = new Weapon(Items.sword);
                mob.health = mob.type.getHealth();
                gameScreen.world.addMob(mob);
                Vars.player = mob;
                Vars.screenController.startGame(gameScreen);
                Events.resize.fire();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        table.center();
        table.add(eatDefenceStartButton).row();

        Group group = new Group();
        group.addActor(table);
        left().bottom().add(group);
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        table.setSize(getWidth(), getHeight());
    }
}
