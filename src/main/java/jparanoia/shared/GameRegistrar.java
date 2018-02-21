package jparanoia.shared;

public class GameRegistrar {
       public static String formattedDesc = "";

    public static JPGameInfo[] getGames() {
        return null;
//
//        if ( !urlsLoaded( true ) ) {
//            return null;
//        }
//
//        ArrayList localArrayList = new ArrayList( 10 );
//        try {
//
//            BufferedReader localBufferedReader = HttpPoster.postToURL( JPURLs.gameRegistrarURL, "description=getgames" );
//            String str;
//
//
//            while ( ( str = localBufferedReader.readLine() ) != null ) {
//
//
//
//                StringTokenizer localStringTokenizer = new StringTokenizer( str, "\t" );
//
//
//                if ( localStringTokenizer.countTokens() == 2 ) {
//
//                    localArrayList.add( new JPGameInfo( localStringTokenizer.nextToken(), localStringTokenizer.nextToken() ) );
//                }
//            }
//
//
//            localBufferedReader.close();
//        } catch ( IOException localIOException ) {
//
//            localIOException.printStackTrace();
//
//            JParanoia.errorMessage( "Network Error", "Unable to get games from game registry:\nRegistry inaccessible. Check your network connection.\n(JParanoia server may be down.)" );
//
//
//            return null;
//        }
//
//        JPGameInfo[] arrayOfJPGameInfo = (JPGameInfo[]) localArrayList.toArray( new JPGameInfo[localArrayList.size()] );
//
//
//        return arrayOfJPGameInfo;
    }

    private static boolean urlsLoaded( boolean paramBoolean ) {
        return false;
//
//        if ( JPURLs.gameRegistrarURL == null ) {
//            try {
//
//                JPURLs.getURLs();
//            } catch ( IOException localIOException ) {
//
//                if ( paramBoolean ) {
//
//                    localIOException.printStackTrace();
//
//                    JParanoia.errorMessage( "Network Error", "Unable to acquire game registry URL:\nServer inaccessible. Check your network connection." );
//                }
//
//
//
//
//                return false;
//            }
//        }
//
//
//        return true;
    }

    public static void addGame( String paramString ) {
        System.out.println("Not adding game to the list as paranoia-live is obviously dead");
//        return;
//
//
//        if ( !urlsLoaded( true ) ) {
//            return;
//        }
//        try {
//
//            BufferedReader localBufferedReader = HttpPoster.postToURL( JPURLs.gameRegistrarURL, "description=" +
//                    paramString );
//
//            int i = -1;
//            String str;
//
//            while ( ( str = localBufferedReader.readLine() ) != null ) {
//            }
//
//            localBufferedReader.close();
//
//            jparanoia.server.JPServer.gameRegistered = true;
//        } catch ( IOException localIOException ) {
//
//            localIOException.printStackTrace();
//
//            JParanoia.errorMessage( "Network Error", "Unable to add game to game registry:\nRegistry inaccessible. Check your network connection." );
//
//            return;
//        }
    }

    public static void removeGame() {
//
//        if ( !urlsLoaded( true ) ) {
//            return;
//        }
//        try {
//
//            BufferedReader localBufferedReader = HttpPoster.postToURL( JPURLs.gameRegistrarURL, "erase=1" );
//            String str;
//
//
//            while ( ( str = localBufferedReader.readLine() ) != null ) {
//            }
//
//            localBufferedReader.close();
//
//
//            jparanoia.server.JPServer.gameRegistered = false;
//        } catch ( IOException localIOException ) {
//
//            localIOException.printStackTrace();
//
//            JParanoia.errorMessage( "Network Error", "Unable to erase game from game registry:\nRegistry inaccessible. Check your network connection." );
//        }
    }

    public static void deleteUnreachableGame( String paramString ) {
//
//        if ( !urlsLoaded( true ) ) {
//            return;
//        }
//
//        System.out.println( "Attempting to remove unreachable game " + paramString + " ..." );
//        try {
//
//            BufferedReader localBufferedReader = HttpPoster.postToURL( JPURLs.gameRegistrarURL, "description=JP-REMOVE:" +
//                    paramString );
//            String str;
//
//
//            while ( ( str = localBufferedReader.readLine() ) != null ) {
//            }
//
//            localBufferedReader.close();
//        } catch ( IOException localIOException ) {
//
//            localIOException.printStackTrace();
//
//            JParanoia.errorMessage( "Network Error", "Unable to remove unreachable game from game registry:\nRegistry inaccessible. Check your network connection." );
//
//        }
    }

    public static String getIP() {
        //FIXME: get public IPs modern way
        return "go figure!";
//
//        if ( !urlsLoaded( false ) ) {
//            return "fail";
//        }
//        try {
//
//            BufferedReader localBufferedReader = HttpPoster.postToURL( JPURLs.gameRegistrarURL, "description=getip" );
//
//
//
//            String str2 = null;
//            String str1;
//
//            while ( ( str1 = localBufferedReader.readLine() ) != null ) {
//
//
//
//                if (str1.contains("JP-REMOTE-IP")) {
//
//                    str2 = str1.substring( str1.indexOf( ":" ) + 1 );
//                }
//            }
//
//
//
//            localBufferedReader.close();
//
//            return str2;
//        } catch ( IOException localIOException ) {
//        }
//
//        return "fail";
    }
}
