package stormcrowmod.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.evacipated.cardcrawl.mod.stslib.extraeffects.ExtraEffectModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import stormcrowmod.actions.PulseAction;

import static stormcrowmod.StormcrowMod.makeID;

public class PulseBeforeMod extends AbstractCardModifier {
    private static final String ID = makeID("PulseBefore");

    public PulseBeforeMod() {

    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        addToTop(new PulseAction());
    }

    @Override
    public boolean removeOnCardPlayed(AbstractCard card) {
        return true;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new PulseBeforeMod();
    }

}
