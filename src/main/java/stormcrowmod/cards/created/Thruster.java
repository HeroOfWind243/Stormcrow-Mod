package stormcrowmod.cards.created;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import stormcrowmod.actions.PulseAction;
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

        AbstractPower p = null;
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(makeID("MorePower"))) {
            p = AbstractDungeon.player.getPower(makeID("MorePower"));
        }

        int bonus = 0;
        if (p != null) {
            bonus = p.amount;
        }
        setBlock(BLOCK + bonus, UPG_BLOCK);

        this.updateDescription();

        setExhaust(true);
        tags.add(PilotTags.THRUSTER);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, 1));

        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(makeID("PulseThrusters"))) {
            addToBot(new PulseAction(new ApplyPowerAction(p, p, new DexterityPower(p, 1)), new ApplyPowerAction(p, p, new LoseDexterityPower(p, 1))));
        }

        updateDescription();
    }

    @Override
    public void triggerWhenDrawn() {
        this.applyPowers();
        addToTop(new GainBlockAction(AbstractDungeon.player, this.block, true));
        updateDescription();
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

        updateDescription();
    }

    public void updateDescription() {
        this.rawDescription = cardStrings.DESCRIPTION;
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(makeID("PulseThrusters"))) {
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
            if (!this.hasTag(PilotTags.PULSE)) {
                this.tags.add(PilotTags.PULSE);
            }
        }
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Thruster();
    }
}
