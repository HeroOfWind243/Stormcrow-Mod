package stormcrowmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import stormcrowmod.actions.ReactionForceFollowupAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class HighJumpKick extends BaseCard {

    public static final String ID = makeID(HighJumpKick.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int DAMAGE = 5;
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = -1;

    private static final int REQ_DMG = 10;

    public HighJumpKick() {
        super(ID, info);
        setDamage(DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("reqDmg", REQ_DMG);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        if (this.damage >= customVar("reqDmg")) {
            addToBot(new GainEnergyAction(1));
            addToBot(new DrawCardAction(p, 1, false));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new HighJumpKick();
    }

    @Override
    public void applyPowers() {

        int bonus = 0;

        int realBaseDamage = this.baseDamage;
        bonus = this.currentMomentum(AbstractDungeon.player);
        bonus = (int) Math.floor(((float)bonus) / this.magicNumber);
        this.baseDamage += bonus;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int bonus = 0;

        int realBaseDamage = this.baseDamage;
        bonus = this.currentMomentum(AbstractDungeon.player);
        bonus = (int) Math.floor(((float)bonus) / this.magicNumber);
        this.baseDamage += bonus;
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

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (this.damage >= customVar("reqDmg")) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

}
