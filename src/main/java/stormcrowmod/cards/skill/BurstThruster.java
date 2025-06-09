package stormcrowmod.cards.skill;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.ShowAndBurnCardsAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.KillThrusterPower;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class BurstThruster extends BaseCard {
    public static final String ID = makeID(BurstThruster.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 4;

    public BurstThruster() {
        super(ID, info);

        setMagic(MAGIC);

        setExhaust(true, false);

        cardsToPreview = new Thruster();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        Predicate<AbstractCard> filter = c -> c.hasTag(PilotTags.THRUSTER);

        Consumer<List<AbstractCard>> buffs = c -> {
            for (AbstractCard t : c) {
                if (t instanceof Thruster) {
                    t.applyPowers();
                }
            }
            addToBot(new ShowAndBurnCardsAction(c));
            for (AbstractCard t : c) {
                if (t instanceof Thruster) {
                    ((Thruster)t).grantBuffs(p);
                }
            }
        };

        addToBot(new ApplyPowerAction(p, p, new KillThrusterPower(p)));
        addToBot(new MoveCardsAction(p.exhaustPile, p.drawPile, filter, 99, buffs));
        addToBot(new RemoveSpecificPowerAction(p, p, KillThrusterPower.POWER_ID));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.hasTag(PilotTags.THRUSTER)) {
                count++;
            }
        }
        if (this.upgraded) {
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        } else {
            this.rawDescription = cardStrings.DESCRIPTION;
        }

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
        return new BurstThruster();
    }
}
