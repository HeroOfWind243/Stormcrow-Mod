package stormcrowmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.PulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Skip;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class SkipShot extends BaseCard {
    public static final String ID = makeID(SkipShot.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            0 //Can use -1 for X, or -2 for unplayable
    );

    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 1;

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public SkipShot() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);

        tags.add(PilotTags.PULSE);
        cardsToPreview = new Skip();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractCard card2 = new Skip(this.magicNumber);

        if (this.upgraded) {
            card2.upgrade();
        }

        addToBot(new PulseAction(new MakeTempCardInHandAction(card2)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SkipShot();
    }
}
