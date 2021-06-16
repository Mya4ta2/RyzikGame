package ryzik.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import ryzik.Vars;

public class ContentSelect extends Group {
    private BackgroundActor background;
    private Table table;
    private int separator;

    public ContentSelect() {
        background = new BackgroundActor(Vars.atlas.find("dialogBackground"));
        addActor(background);

        table = new Table();
        table.left().bottom();
        addActor(table);

        setDefaultSize();
        separator = 6;

        fill();
    }

    public void fill() {
        Array<ContentSlot> tempContentSlots = new Array<>();

        for (int i = 0; i < Vars.content.content.size; i++) {
            ContentSlot contentSlot = new ContentSlot(
                    Vars.atlas.find("dialogBackground"),
                    Vars.content.content.get(i)
            );

            tempContentSlots.add(contentSlot);
            if (tempContentSlots.size * contentSlot.getWidth() > getWidth()) {
                tempContentSlots.clear();
                table.row();
                table.add(new Separator(separator, separator));
                table.row();
            }

            table.add(contentSlot);
            table.add(new Separator(separator, separator));
        }
    }

    public void setDefaultSize() {
        setWidth(256);
        setHeight(256);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    protected void positionChanged() {
        background.setPosition(getX(), getY());
        table.setPosition(getX(), getY());
    }

    @Override
    protected void sizeChanged() {
        background.setSize(getWidth(), getHeight());
        table.setSize(getWidth(), getHeight());
    }
}
