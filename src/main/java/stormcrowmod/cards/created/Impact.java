package stormcrowmod.cards.created;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import stormcrowmod.actions.ReactionForceFollowupAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.MomentumPower;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class Impact extends BaseCard {
    private static final int BASE_COST = 1;


    public static final String ID = makeID(Impact.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            BASE_COST //Can use -1 for X, or -2 for unplayable
    );

    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 6;


    private int dmgFactor = 1;

    public Impact() {
        super(ID, info);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(0);

        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(makeID("Impulsive"))) {
            this.costForTurn = BASE_COST + 1;
            dmgFactor = 2;
            this.setSelfRetain(false);
            this.setEthereal(true);
        } else {
            dmgFactor = 1;
            this.setSelfRetain(true);
            this.setEthereal(false);
        }

        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(makeID("PlowThrough"))) {
            this.costForTurn = this.costForTurn - AbstractDungeon.player.getPower(makeID("PlowThrough")).amount;
            if (this.costForTurn < 0) {
                this.costForTurn = 0;
            }
        }

        if (this.costForTurn != BASE_COST) {
            this.isCostModified = true;
        }

        setExhaust(true);

        tags.add(PilotTags.IMPACT);

        updateDescription();

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Crater Handler
        if (p.hasPower(makeID("Crater"))) {
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                addToBot(new VFXAction(new VerticalImpactEffect(mo.hb.cX + mo.hb.width / 4.0F, mo.hb.cY - mo.hb.height / 4.0F)));
            }
            addToBot(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
        else {
            if (m != null)
                addToBot(new VFXAction(new VerticalImpactEffect(m.hb.cX + m.hb.width / 4.0F, m.hb.cY - m.hb.height / 4.0F)));
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }

        //Unstoppable Form Handler
//        if (!p.hasPower(makeID("UnstoppableForm"))) {
//            int half_m_rounded_up = (int) Math.ceil(this.currentMomentum(AbstractDungeon.player) / 2.0);
//            addToBot(new ReducePowerAction(p, p, makeID("Momentum"), half_m_rounded_up));
//        }

        //Reaction Force Handler
        if (p.hasPower(makeID("ReactionForce"))) {
            addToBot(new ReactionForceFollowupAction(p));
        }

        this.isCostModified = false;

    }

    @Override
    public AbstractCard makeCopy() {
        return new Impact();
    }

    @Override
    public void applyPowers() {
        if (AbstractDungeon.player.hasPower(makeID("Crater"))) {
            this.isMultiDamage = true;
            this.target = CardTarget.ALL_ENEMY;
        } else {
            this.isMultiDamage = false;
            this.target = CardTarget.ENEMY;
        }

        if (AbstractDungeon.player.hasPower(makeID("Impulsive"))) {
            this.costForTurn = BASE_COST + 1;
            dmgFactor = 2;
            this.setSelfRetain(false);
            this.setEthereal(true);
        } else {
            dmgFactor = 1;
            this.setSelfRetain(true);
            this.setEthereal(false);
        }

        if (AbstractDungeon.player.hasPower(makeID("PlowThrough"))) {
            this.costForTurn = this.costForTurn - AbstractDungeon.player.getPower(makeID("PlowThrough")).amount;
            if (this.costForTurn < 0) {
                this.costForTurn = 0;
            }
        }

        if (this.costForTurn != BASE_COST) {
            this.isCostModified = true;
        } else {
            this.isCostModified = false;
        }

        int realBaseDamage = this.baseDamage;
        this.baseMagicNumber = this.currentMomentum(AbstractDungeon.player);
        this.baseDamage += this.baseMagicNumber;
        this.baseDamage *= dmgFactor;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);

        updateDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        if (AbstractDungeon.player.hasPower(makeID("Crater"))) {
            this.isMultiDamage = true;
            this.target = CardTarget.ALL_ENEMY;
        } else {
            this.isMultiDamage = false;
            this.target = CardTarget.ENEMY;
        }

        if (AbstractDungeon.player.hasPower(makeID("Impulsive"))) {
            this.costForTurn = BASE_COST + 1;
            dmgFactor = 2;
        } else {
            dmgFactor = 1;
        }

        if (this.costForTurn != BASE_COST) {
            this.isCostModified = true;
        } else {
            this.isCostModified = false;
        }

        this.baseMagicNumber = this.currentMomentum(AbstractDungeon.player);
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.baseMagicNumber;
        this.baseDamage *= dmgFactor;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);

        updateDescription();
    }

    private int currentMomentum(AbstractPlayer p) { //Check that avoids returning null if Momentum is not present
        if (p.hasPower(makeID("Momentum"))) {
            return p.getPower(makeID("Momentum")).amount;
        }
        return 0;
    }

    private void updateDescription() {
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(makeID("Impulsive"))) {
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[1] + cardStrings.DESCRIPTION;
        } else {
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.DESCRIPTION;
        }
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
//        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(makeID("UnstoppableForm"))) {
//            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[3];
//        } else {
//            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2] + cardStrings.EXTENDED_DESCRIPTION[3];
//        }
        initializeDescription();
    }
}
