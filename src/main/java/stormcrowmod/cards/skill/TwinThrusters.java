package stormcrowmod.cards.skill;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.ImpulseAction;
import stormcrowmod.actions.ShowAndBurnCardsAction;
import stormcrowmod.actions.TwinThrustersAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Impact;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.cards.created.ThrusterV2;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.KillThrusterPower;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

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
        this.cardsToPreview.upgrade();
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
        int countDis = 0;
        int countDraw = 0;
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.hasTag(PilotTags.THRUSTER)) {
                countDraw++;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.hasTag(PilotTags.THRUSTER)) {
                countDis++;
            }
        }

        int count = 0;

        if (countDraw > countDis) {
            count = countDis;
        } else {
            count = countDraw;
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
