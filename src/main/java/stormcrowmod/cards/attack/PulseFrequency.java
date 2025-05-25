package stormcrowmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import stormcrowmod.actions.PulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class PulseFrequency extends BaseCard {
    public static final String ID = makeID(PulseFrequency.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2 //Can use -1 for X, or -2 for unplayable
    );

    // Solo Hit dmg
    private static final int DAMAGE_1 = 14;
    private static final int UPG_DAMAGE_1 = 3;
    //Weakness debuff
    private static final int MAGIC_1 = 2;
    private static final int UPG_MAGIC_1 = 1;
    //Card draw
    private static final int MAGIC_2 = 2;
    private static final int UPG_MAGIC_2 = 1;
    // Multi Hit dmg
    private static final int DAMAGE_2 = 6;
    private static final int UPG_DAMAGE_2 = 1;


    public PulseFrequency() {
        super(ID, info);

        setDamage(DAMAGE_1, UPG_DAMAGE_1);
        setCustomVar("damage2", DAMAGE_2, UPG_DAMAGE_2);
        setMagic(MAGIC_1, UPG_MAGIC_1);
        setCustomVar("magic2", MAGIC_2, UPG_MAGIC_2);

        tags.add(PilotTags.PULSE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        calculateVarAsDamage("damage2");

        addToBot(new PulseAction(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL)));
        addToBot(new PulseAction(new DamageAction(m, new DamageInfo(p, customVar("damage2"), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL), new DamageAction(m, new DamageInfo(p, customVar("damage2"), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL)));
        addToBot(new PulseAction(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false))));
        addToBot(new PulseAction(new DrawCardAction(p, customVar("magic2"))));

    }

    @Override
    public void applyPowers() {
        calculateVarAsDamage("damage2");
    }

    @Override
    public AbstractCard makeCopy() {
        return new PulseFrequency();
    }
}
