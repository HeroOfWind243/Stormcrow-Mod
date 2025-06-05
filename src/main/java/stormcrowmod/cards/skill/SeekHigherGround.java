package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.ImpulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.VulnerableNextTurnPower;
import stormcrowmod.util.CardStats;

public class SeekHigherGround extends BaseCard {
    public static final String ID = makeID(SeekHigherGround.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.ALL,
            2 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    private static final int BLOCK = 13;
    private static final int UPG_BLOCK = 3;

    public SeekHigherGround() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new GainBlockAction(p, this.block));
        addToBot(new ApplyPowerAction(p, p, new VulnerableNextTurnPower(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SeekHigherGround();
    }
}
