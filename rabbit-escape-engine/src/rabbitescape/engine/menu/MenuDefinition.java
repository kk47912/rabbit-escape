package rabbitescape.engine.menu;

import static rabbitescape.engine.menu.MenuConstruction.*;
import static rabbitescape.engine.util.Util.*;

import rabbitescape.engine.menu.LevelsList.LevelSetInfo;
import rabbitescape.engine.menu.MenuItem.Type;
import rabbitescape.engine.util.Util.IdxObj;

public class MenuDefinition
{
    
    public static final LevelsList allLevels = new LevelsList(
        new LevelSetInfo( "Easy", "01_easy", null ),
        new LevelSetInfo( "Medium", "02_medium", null ),
        new LevelSetInfo( "Hard", "03_hard", null ),
        new LevelSetInfo( "Outdoors", "04_outdoors", null ),
        new LevelSetInfo( "Arcade", "05_arcade", null ),
        new LevelSetInfo( "Small World", "06_small_world", null ),
        new LevelSetInfo( "Deja Vu", "07_dejavu", null ),
        new LevelSetInfo( "Rabbots", "08_rabbots", null ),
        new LevelSetInfo( "Development", "development", null, true ),
        new LevelSetInfo( "Staging", "staging", null, true )
    );

    public static Menu mainMenu(
        LevelsCompleted levelsCompleted,
        LevelsList loadedLevels,
        boolean includeLoadLevel
    )
    {
        return menu(
            "Rabbit Escape에 오신 것을 환영합니다!",
            item(
                "게임 시작",
                menu(
                    "스테이지를 선택하십시오:",
                    items( levelsCompleted, loadedLevels )
                ),
                true,
                false
            ),
            item( "Rabbit Escape 정보", Type.ABOUT, true ),
            maybeItem(
                includeLoadLevel,
                "사용자 지정 레벨",
                menu(
                    "파일 또는 네트워크에서 가져 오기",
                    item( "레벨 가져오기", Type.LOAD, true ),
                    item( "GitHub 이슈", Type.GITHUB_ISSUE, true )
                ),
                true
            ),
            item( "종료", Type.QUIT,  true )
        );
    }

    private static MenuItem[] items(
        LevelsCompleted levelsCompleted,
        LevelsList loadedLevels
    )
    {
        MenuItem[] ret = new MenuItem[ loadedLevels.size() ];
        for ( IdxObj<LevelSetInfo> setI : enumerate( loadedLevels ) )
        {
            LevelSetInfo set = setI.object;
            ret[setI.index] = item(
                set.name,
                new LevelsMenu( set.dirName, loadedLevels, levelsCompleted ),
                true,
                set.hidden
            );
        }
        return ret;
    }
}
