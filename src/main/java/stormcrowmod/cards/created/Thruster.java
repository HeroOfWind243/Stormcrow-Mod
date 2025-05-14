package stormcrowmod.cards.created;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class Thruster extends BaseCard {
    public static final String ID = makeID(Thruster.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0 //Can use -1 for X, or -2 for unplayable
    );

    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 2;

    public Thruster() {
        super(ID, info);
        // TODO: Add checks for Pulse Thrusters here
        AbstractPower p = null;
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(makeID("MorePower"))) {
            p = AbstractDungeon.player.getPower(makeID("MorePower"));
        }

        int bonus = 0;
        if (p != null) {
            bonus = p.amount;
        }
        setBlock(BLOCK + bonus, UPG_BLOCK);

        this.initializeDescription();

        setExhaust(true);
        tags.add(PilotTags.THRUSTER);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, 1));
        initializeDescription();
    }

    @Override
    public void triggerWhenDrawn() {
        this.applyPowers();
        addToTop(new GainBlockAction(AbstractDungeon.player, this.block, true));
        initializeDescription();
    }

    @Override
    public void applyPowers() {
            this.baseBlock = BLOCK;
            AbstractPower p = (AbstractDungeon.player.getPower(makeID("MorePower")));
            int bonus = 0;
            if (p != null) {
                bonus = p.amount;
            }
            this.baseBlock += bonus;
            if (this.upgraded) {
                this.baseBlock += UPG_BLOCK;
            }
            super.applyPowers();

            initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Thruster();
    }
}
