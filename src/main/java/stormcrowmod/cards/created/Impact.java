package stormcrowmod.cards.created;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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
    public static final String ID = makeID(Impact.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 6;

    public Impact() {
        super(ID, info);
        setSelfRetain(true);
        setExhaust(true);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(0);

        tags.add(PilotTags.IMPACT);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null)
            addToBot(new VFXAction(new VerticalImpactEffect(m.hb.cX + m.hb.width / 4.0F, m.hb.cY - m.hb.height / 4.0F)));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        int half_m_rounded_up = (int)Math.ceil(this.currentMomentum(AbstractDungeon.player) / 2.0);
        addToBot(new ReducePowerAction(p,p, makeID("Momentum"), half_m_rounded_up));

        if (p.hasPower(makeID("ReactionForce"))) {
            addToBot(new ReactionForceFollowupAction(p));
        }

    }

    @Override
    public AbstractCard makeCopy() {
        return new Impact();
    }

    @Override
    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseMagicNumber = this.currentMomentum(AbstractDungeon.player);
        this.baseDamage += this.baseMagicNumber;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        this.baseMagicNumber = this.currentMomentum(AbstractDungeon.player);
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.baseMagicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    private int currentMomentum(AbstractPlayer p) { //Check that avoids returning null if Momentum is not present
        if (p.hasPower(makeID("Momentum"))) {
            return p.getPower(makeID("Momentum")).amount;
        }
        return 0;
    }
}
