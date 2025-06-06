package stormcrowmod.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static stormcrowmod.StormcrowMod.makeID;

public class KillThrusterPower extends BasePower implements InvisiblePower{
    public static final String POWER_ID = makeID("KillThruster");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    // This is used by other cards to interrupt Thrusters from drawing new cards
    public KillThrusterPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

}
