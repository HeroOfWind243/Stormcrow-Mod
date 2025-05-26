package stormcrowmod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.ImpulseAction;
import stormcrowmod.util.PilotTags;

import static stormcrowmod.StormcrowMod.makeID;

public class CraterPower extends BasePower {
    public static final String POWER_ID = makeID("Crater");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    // The actual effect takes place in the Impact Card
    public CraterPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, 1);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(PilotTags.IMPACT)) {
            flash();
            this.amount--;
            if (this.amount == 0) {
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, makeID("Crater")));
            }
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, makeID("Crater")));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
