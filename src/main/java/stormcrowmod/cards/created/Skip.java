package stormcrowmod.cards.created;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.PulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class Skip extends BaseCard {
    public static final String ID = makeID(Skip.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.ATTACK,
            CardRarity.SPECIAL,
            CardTarget.ENEMY,
            0 //Can use -1 for X, or -2 for unplayable
    );

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 1;

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 1;

    private final int bonusDmg;

    public Skip() {
        super(ID, info);

        this.bonusDmg = 0;

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);

        setExhaust(true);
        setEthereal(true);

        tags.add(PilotTags.PULSE);
    }

    public Skip(int bonusDmg) {
        super(ID, info);

        this.bonusDmg = bonusDmg;

        setCustomVar("magic2", this.bonusDmg);

        setDamage(DAMAGE + this.bonusDmg, UPG_DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);

        setExhaust(true);
        setEthereal(true);

        tags.add(PilotTags.PULSE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

        AbstractCard card2 = new Skip(this.magicNumber + customVar("magic2"));

        if (this.upgraded) {
            card2.upgrade();
        }

        addToBot(new PulseAction(new MakeTempCardInHandAction(card2)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Skip();
    }
}
