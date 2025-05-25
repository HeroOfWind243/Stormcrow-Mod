package stormcrowmod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import stormcrowmod.actions.ImpulseAction;

import static stormcrowmod.StormcrowMod.makeID;

public class NextTurnImpulsePower extends BasePower {
    public static final String POWER_ID = makeID("NextTurnImpulse");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    // The actual effect takes place in the PulseAction
    public NextTurnImpulsePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        flash();
        addToBot(new ImpulseAction(owner, amount));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
