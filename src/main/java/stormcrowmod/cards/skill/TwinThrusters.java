package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.ImpulseAction;
import stormcrowmod.actions.TwinThrustersAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Impact;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class TwinThrusters extends BaseCard {
    public static final String ID = makeID(TwinThrusters.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    public TwinThrusters() {
        super(ID, info);
        setCostUpgrade(0);

        this.cardsToPreview = new Thruster();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new TwinThrustersAction(p));
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(PilotTags.THRUSTER)) {
                count++;
            }
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        if (count == 1) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        }
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new TwinThrusters();
    }
}
