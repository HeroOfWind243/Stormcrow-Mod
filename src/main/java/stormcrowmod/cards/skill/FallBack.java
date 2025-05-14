package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.MomentumPower;
import stormcrowmod.util.CardStats;

public class FallBack extends BaseCard {
    public static final String ID = makeID(FallBack.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 2;

    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 2;

    public FallBack() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new GainBlockAction(p, this.block));
        addToBot(new ApplyPowerAction(p, p, new MomentumPower(p,this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new FallBack();
    }
}
