package stormcrowmod.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.ThrustVectoringPower;
import stormcrowmod.util.CardStats;

public class ThrustVectoring extends BaseCard {
    public static final String ID = makeID(ThrustVectoring.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public ThrustVectoring() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_STRENGTH", 0.05F);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ThrustVectoringPower(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ThrustVectoring();
    }
}
