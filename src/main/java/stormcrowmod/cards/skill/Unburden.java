package stormcrowmod.cards.skill;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.ExhaustCardAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.MomentumPower;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

import java.util.List;
import java.util.function.Consumer;

public class Unburden extends BaseCard {
    public static final String ID = makeID(Unburden.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 2;

    private static final int MAGIC_2 = 5;
    private static final int UPG_MAGIC_2 = 0;

    public Unburden() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("magic2", MAGIC_2, UPG_MAGIC_2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        Consumer<List<AbstractCard>> card = list -> {
            for (AbstractCard c : list) {
                addToTop(new ApplyPowerAction(p, p, new MomentumPower(p, customVar("magic2"))));
                addToTop(new ExhaustCardAction(c));
            }
        };

        addToBot(new SelectCardsInHandAction(this.magicNumber,"Exhaust",true, true,(c) -> true , card));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Unburden();
    }
}
