package stormcrowmod.cards.created;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.powers.KillThrusterPower;
import stormcrowmod.powers.ScorchThrustersPower;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class Thruster extends BaseCard {
    public static final String ID = makeID(Thruster.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            -2 //Can use -1 for X, or -2 for unplayable
    );

    //Strength Magic
    private static final int SAGIC = 1;
    private static final int UPG_SAGIC = 1;

    //Dexterity Magic
    private static final int DAGIC = 1;
    private static final int UPG_DAGIC = 1;

    public Thruster() {
        super(ID, info);

        setMagic(SAGIC, UPG_SAGIC);
        setCustomVar("dagic", DAGIC, UPG_DAGIC);

        this.updateDescription();

        tags.add(PilotTags.THRUSTER);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public void triggerWhenDrawn() {
        this.applyPowers();
        updateDescription();

        AbstractPlayer p = AbstractDungeon.player;

        this.grantBuffs(p);

        if (p.hasPower(ScorchThrustersPower.POWER_ID)) {
            AbstractPower scorch = p.getPower(ScorchThrustersPower.POWER_ID);
            scorch.flash();
            int dmg = scorch.amount;
            addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(dmg,false), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE, true));
        }

        if (!p.hasPower(KillThrusterPower.POWER_ID)) {
            addToBot(new DiscardSpecificCardAction(this));
            addToBot(new DrawCardAction(p, 1));
        }

    }

    public void grantBuffs(AbstractPlayer p) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber, true));
        addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, this.magicNumber), this.magicNumber, true));

        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, customVar("dagic")), customVar("dagic"), true));
        addToBot(new ApplyPowerAction(p, p, new LoseDexterityPower(p, customVar("dagic")), customVar("dagic"), true));
    }

    @Override
    public void applyPowers() {
        AbstractPower p = (AbstractDungeon.player.getPower(makeID("MorePower")));
        int bonus = 0;
        if (p != null) {
            bonus = p.amount;
        }
        this.magicNumber += bonus;
        super.applyPowers();

        updateDescription();
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void updateDescription() {
        //this.rawDescription = cardStrings.DESCRIPTION;
//        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(makeID("PulseThrusters"))) {
//            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
//            if (!this.hasTag(PilotTags.PULSE)) {
//                this.tags.add(PilotTags.PULSE);
//            }
//        }
        //this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Thruster();
    }
}
