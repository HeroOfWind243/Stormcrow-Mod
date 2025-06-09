package stormcrowmod.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import stormcrowmod.cards.created.Invert;
import stormcrowmod.util.PilotTags;

import static stormcrowmod.StormcrowMod.makeID;

public class PulseThrustersPower extends BasePower {
    public static final String POWER_ID = makeID("PulseThrusters");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static final int TRIGGER_COUNT = 4;

    public PulseThrustersPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.amount2 = TRIGGER_COUNT;
        updateDescription();
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card.hasTag(PilotTags.THRUSTER)) {
            amount2--;
            updateDescription();
        }

        if (amount2 <= 0) {
            flash();
            addToBot(new MakeTempCardInHandAction(new Invert(), this.amount));
            amount2 = TRIGGER_COUNT;
            updateDescription();
        }

    }


    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + TRIGGER_COUNT + DESCRIPTIONS[1] + amount;
        if (amount > 1) {
            this.description += DESCRIPTIONS[3];
        } else {
            this.description += DESCRIPTIONS[2];
        }
        this.description += DESCRIPTIONS[4] + amount2 + DESCRIPTIONS[5];
    }
}
