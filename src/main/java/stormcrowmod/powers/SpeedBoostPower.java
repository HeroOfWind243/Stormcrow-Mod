package stormcrowmod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static stormcrowmod.StormcrowMod.makeID;

public class SpeedBoostPower extends BasePower {
    public static final String POWER_ID = makeID("SpeedBoost");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public SpeedBoostPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (owner instanceof AbstractPlayer && owner.hasPower(makeID("SBCheck"))) {
            addToBot(new ApplyPowerAction(owner, owner, new MomentumPower(owner, this.amount)));
        }
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new ApplyPowerAction(owner, owner, new SBCheckPower(owner)));
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        addToTop(new RemoveSpecificPowerAction(owner, owner, makeID("SBCheck")));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
