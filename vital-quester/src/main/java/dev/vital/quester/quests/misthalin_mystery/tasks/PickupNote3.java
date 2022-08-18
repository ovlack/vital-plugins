package dev.vital.quester.quests.misthalin_mystery.tasks;

import dev.vital.quester.ScriptTask;
import dev.vital.quester.VitalQuesterConfig;
import dev.vital.quester.tasks.BasicTask;
import dev.vital.quester.tools.Tools;
import net.runelite.api.ItemID;
import net.runelite.api.coords.WorldPoint;
import net.unethicalite.api.game.Vars;
import net.unethicalite.api.items.Inventory;
import net.unethicalite.api.quests.QuestVarbits;

public class PickupNote3 implements ScriptTask
{
    WorldPoint note_point = new WorldPoint(1631, 4842, 0);

    VitalQuesterConfig config;

    public PickupNote3(VitalQuesterConfig config)
    {
        this.config = config;
    }

    @Override
    public boolean validate()
    {
        return Vars.getBit(QuestVarbits.QUEST_MISTHALIN_MYSTERY.getId()) == 90;
    }

    BasicTask pickup_note = new BasicTask(() -> {
        if(Inventory.contains(ItemID.NOTES_21058)) {
            Inventory.getFirst(ItemID.NOTES_21058).interact("Read");
            return 0;
        }
        else{
            Tools.interactWith(29645, "Take", note_point, Tools.EntityType.TILE_OBJECT);
        }
        return -5;
    });

    @Override
    public int execute() {

        if (!pickup_note.taskCompleted()) {
            return pickup_note.execute();
        }

        return -1;
    }
}
