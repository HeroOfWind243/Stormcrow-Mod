package stormcrowmod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import stormcrowmod.actions.ImpulseAction;

import static stormcrowmod.StormcrowMod.makeID;

public class UnstoppablePower extends BasePower {
    public static final String POWER_ID = makeID("UnstoppableForm");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    // The actual effect takes place in the Impact Card
    public UnstoppablePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        flash();
        addToBot(new ImpulseAction(owner, amount));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
