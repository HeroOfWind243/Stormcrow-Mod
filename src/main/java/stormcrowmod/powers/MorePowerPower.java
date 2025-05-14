package stormcrowmod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static stormcrowmod.StormcrowMod.makeID;

public class MorePowerPower extends BasePower {
    public static final String POWER_ID = makeID("MorePower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public MorePowerPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
