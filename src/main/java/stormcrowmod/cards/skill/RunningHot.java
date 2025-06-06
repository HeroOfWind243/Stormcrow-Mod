package stormcrowmod.cards.skill;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.AdrenalineEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import stormcrowmod.actions.ReThrustAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RunningHot extends BaseCard {
    public static final String ID = makeID(RunningHot.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            3 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 3;

    public RunningHot() {
        super(ID, info);
        setCostUpgrade(2);
        setExhaust(true);
        setMagic(MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Consumer<List<AbstractCard>> cards = c -> {
            addToTop(new GainEnergyAction(c.size()));
            for (AbstractCard card : c) {
                addToTop(new DiscardSpecificCardAction(card));
            }
            addToTop(new VFXAction(new AdrenalineEffect()));
        };
        addToBot(new DrawCardAction(p, this.magicNumber));
        addToBot(new SelectCardsInHandAction(99,"Discard",true, true, c -> true, cards));

    }

    @Override
    public AbstractCard makeCopy() {
        return new RunningHot();
    }
}
