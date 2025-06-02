package stormcrowmod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static stormcrowmod.StormcrowMod.makeID;

public class ThrustVectoringPower extends BasePower {
    public static final String POWER_ID = makeID("ThrustVectoring");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ThrustVectoringPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        int cardAmount = AbstractDungeon.player.hand.group.size();
        //addToBot(new GainBlockAction(this.owner, this.owner, cardAmount * amount));
        addToBot(new ApplyPowerAction(this.owner, this.owner, new MomentumPower(this.owner, cardAmount * amount)));
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        if (amount > 1) {
            this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2] + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
        }
    }
}
