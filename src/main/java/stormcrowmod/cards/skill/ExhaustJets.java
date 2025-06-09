package stormcrowmod.cards.skill;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.ExhaustCardAction;
import stormcrowmod.actions.ShowAndBurnCardsAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ExhaustJets extends BaseCard {
    public static final String ID = makeID(ExhaustJets.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 2;
//    private static final int MAGIC = 2;

    public ExhaustJets() {
        super(ID, info);

//        setMagic(MAGIC);
        setBlock(BLOCK, UPG_BLOCK);

        cardsToPreview = new Thruster();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        Consumer<List<AbstractCard>> card = list -> {
            for (AbstractCard c : list) {
                if (c.hasTag(PilotTags.THRUSTER)) {
//                    addToBot(new DrawCardAction(p, this.magicNumber));
                    addToBot(new GainBlockAction(p, this.block));
                }
                List<AbstractCard> tmp = new ArrayList<>();
                tmp.add(c);
//                addToBot(new ShowAndBurnCardsAction(tmp));
                addToTop(new ExhaustSpecificCardAction(c, p.drawPile));
            }
        };

//        addToBot(new SelectCardsInHandAction(1, "Exhaust", card));
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : p.drawPile.group)
            tmp.addToRandomSpot(c);
        if (!tmp.isEmpty()) {
            addToBot(new SelectCardsAction(tmp.group, 1, "Exhaust", card));
        }

        addToBot(new GainBlockAction(p, this.block));

    }

    @Override
    public AbstractCard makeCopy() {
        return new ExhaustJets();
    }
}
