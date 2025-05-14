package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import stormcrowmod.actions.PulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.MomentumPower;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class SwitchUp extends BaseCard {
    public static final String ID = makeID(SwitchUp.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;
    private static final int BLOCK_2 = 4;

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;

    public SwitchUp() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("Block2", BLOCK_2);

        tags.add(PilotTags.PULSE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new GainBlockAction(p, customVar("Block2")));
        addToBot(new PulseAction(
                new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber)),
                new ApplyPowerAction(p, p, new LoseStrengthPower(p, this.magicNumber))));
        addToBot(new PulseAction(new GainBlockAction(p, this.block)));
        addToBot(new PulseAction());
    }

    @Override
    public AbstractCard makeCopy() {
        return new SwitchUp();
    }
}
