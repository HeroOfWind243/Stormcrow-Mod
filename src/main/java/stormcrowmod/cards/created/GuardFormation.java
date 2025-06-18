package stormcrowmod.cards.created;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.ImpulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.util.CardStats;

public class GuardFormation extends BaseCard {
    public static final String ID = makeID(GuardFormation.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2 //Can use -1 for X, or -2 for unplayable
    );

    private static final int BLOCK = 9;
    private static final int UPG_BLOCK = 3;


    public GuardFormation() {
        super(ID, info);

        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        AbstractPlayer pl = AbstractDungeon.player;

        addToBot(new GainBlockAction(pl, this.block));
    }

    @Override
    public AbstractCard makeCopy() {
        return new GuardFormation();
    }
}
