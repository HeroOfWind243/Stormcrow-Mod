package stormcrowmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class BruteForce extends BaseCard {
    public static final String ID = makeID(BruteForce.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int DAMAGE = 2;

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public BruteForce() {
        super(ID, info);

        setDamage(DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));

    }

    @Override
    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        int scale = this.totalSkills(AbstractDungeon.player);
        this.baseDamage += scale * this.baseMagicNumber;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int scale = this.totalSkills(AbstractDungeon.player);
        int realBaseDamage = this.baseDamage;
        this.baseDamage += scale * this.baseMagicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    private int totalSkills(AbstractPlayer p) { //Check that avoids returning null if Momentum is not present
        return p.discardPile.getSkills().size();
    }

    @Override
    public AbstractCard makeCopy() {
        return new BruteForce();
    }
}
