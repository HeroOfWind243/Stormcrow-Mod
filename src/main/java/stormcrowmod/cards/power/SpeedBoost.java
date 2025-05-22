package stormcrowmod.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.HopManeuversPower;
import stormcrowmod.powers.SpeedBoostPower;
import stormcrowmod.util.CardStats;

public class SpeedBoost extends BaseCard {
    public static final String ID = makeID(SpeedBoost.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public SpeedBoost() {
        super(ID, info);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SpeedBoostPower(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SpeedBoost();
    }
}
