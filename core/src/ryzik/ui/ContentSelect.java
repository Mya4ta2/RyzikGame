package ryzik.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import ryzik.Vars;
import ryzik.ctype.Content;
import ryzik.type.world.block.Block;
import ryzik.type.world.floor.Floor;

public class ContentSelect extends Group {
    private BackgroundActor background;
    private Table slotsTable;
    private Table table;
    private int separator;

    public ContentSelect() {
        table = new Table();
        slotsTable = new Table();

        background = new BackgroundActor(Vars.atlas.find("dialogBackground"));
        addActor(background);

        table.center().add(slotsTable);
        addActor(table);

        setDefaultSize();
        separator = 6;

        fill();
    }

    public void fill() {
        Array<ContentSlot> tempContentSlots = new Array<>();

        for (int i = 0; i < Vars.content.content.size; i++) {
            if (getContentName(Vars.content.content.get(i)).equals("air")) continue;

            ContentSlot contentSlot = new ContentSlot(
                    Vars.atlas.find("dialogBackground"),
                    Vars.content.content.get(i)
            );

            tempContentSlots.add(contentSlot);
            if (tempContentSlots.size * contentSlot.getWidth() > getWidth()) {
                tempContentSlots.clear();
                slotsTable.row();
                slotsTable.add(new Separator(separator, separator));
                slotsTable.row();
            }

            slotsTable.add(contentSlot);
            slotsTable.add(new Separator(separator, separator));
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
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
    protected void sizeChanged() {
        background.setSize(getWidth(), getHeight());
        table.setSize(getWidth(), getHeight());
        slotsTable.setSize(getWidth(), getHeight());
    }

    private String getContentName(Content content) {
        String name;
        if (content instanceof Block) name = ((Block) content).name;
        else if (content instanceof Floor) name = ((Floor) content).name;
        else throw new RuntimeException();

        return name;
    }
}
