package stormcrowmod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import stormcrowmod.actions.PulseWidthModAction;

import static stormcrowmod.StormcrowMod.makeID;

public class PulseWidthModPower extends BasePower {
    public static final String POWER_ID = makeID("PulseWidthModulator");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public PulseWidthModPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        flash();
        addToBot(new PulseWidthModAction(this.owner, this.amount));
    }

    @Override
    public void updateDescription() {

        if (this.amount > 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }

    }
}
