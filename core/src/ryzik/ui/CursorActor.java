package ryzik.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ryzik.Cursor;
import ryzik.Vars;

public class CursorActor extends Actor {
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (Cursor.selectedItem != null && !Cursor.selectedItem.empty()) {
            batch.draw(
                    Cursor.selectedItem.getItemType().texture,
                    Cursor.x,
                    Cursor.y
            );

            Vars.font.draw(
                    batch,
                    String.valueOf(Cursor.selectedItem.getAmount()),
                    Cursor.x + Cursor.selectedItem.getItemType().texture.getWidth(),
                    Cursor.y
            );
        }
    }
}
