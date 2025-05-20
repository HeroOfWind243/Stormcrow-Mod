package stormcrowmod.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Invert;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.MorePowerPower;
import stormcrowmod.powers.PulseWidthModPower;
import stormcrowmod.util.CardStats;

public class PulseWidthModulator extends BaseCard {
    public static final String ID = makeID(PulseWidthModulator.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 1;

    public PulseWidthModulator() {
        super(ID, info);
        this.cardsToPreview = new Invert();
        setMagic(MAGIC);
        setInnate(false, true);
    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("ORB_SLOT_GAIN", 0.05F);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PulseWidthModPower(p, this.magicNumber)));

    }

    @Override
    public AbstractCard makeCopy() {
        return new PulseWidthModulator();
    }
}
