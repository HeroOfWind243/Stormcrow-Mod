package stormcrowmod.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.AllInPower;
import stormcrowmod.util.CardStats;

public class AllIn extends BaseCard {
    public static final String ID = makeID(AllIn.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            2 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public AllIn() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new AllInPower(p, this.magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new FrailPower(p, 99, false)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new AllIn();
    }
}
