package dev.vital.quester.quests.pirates_treasure.tasks;

import dev.vital.quester.ScriptTask;
import dev.vital.quester.VitalQuesterConfig;
import dev.vital.quester.VitalTask;
import dev.vital.quester.tools.Tools;
import net.runelite.api.ItemID;
import net.runelite.api.coords.WorldPoint;
import net.unethicalite.api.commons.Time;
import net.unethicalite.api.items.Inventory;
import net.unethicalite.api.items.Shop;

public class GetJob implements ScriptTask
{
    private final WorldPoint zambo_location = new WorldPoint(2928, 3144, 0);
    private final WorldPoint luthas_location = new WorldPoint(2938, 3152, 0);

    VitalQuesterConfig config;

    public GetJob(VitalQuesterConfig config)
    {
        this.config = config;
    }

    VitalTask get_job = new VitalTask(() ->
    {
        if(!Tools.interactWith("Luthas", "Talk-to", luthas_location, Tools.EntityType.NPC)) {
            return false;
        }

        return Tools.selectOptions("Could you offer me employment on your plantation?");
    });

    @Override
    public boolean validate()
    {
        return !get_job.taskCompleted();
    }

    @Override
    public int execute() {

        if(!get_job.execute()) {
            return -5;
        }

        return -1;
    }
}
