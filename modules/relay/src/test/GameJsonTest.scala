package lila.relay

import chess.format.pgn.{ Tag, Tags }
import scalalib.model.Seconds

class GameJsonTest extends munit.FunSuite:

  test("clock"):
    val clock =
      DgtJson.ClockJson(white = Some(Seconds(4468)), black = Some(Seconds(30)), time = 1734688185870L)

    assertEquals(
      DgtJson.GameJson(Nil, None, Some(clock)).clockTags,
      Tags(
        List(
          Tag(_.WhiteClock, "4468"),
          Tag(_.BlackClock, "30")
          // Tag(_.ReferenceTime, "2024-12-20T09:49:45.870Z")
        )
      )
    )

  test("toPgn"):

    val moves = List(
      "d4 1852+6",
      "e6 1818+44",
      "Nf3 1862+19",
      "Nc6 1821+28",
      "e4 1851+41",
      "Bb4+ 1741+25",
      "Nc3 1871+94",
      "Bd6 1736+29",
      "Bc4 1831+75",
      "Nf6 1742+22",
      "e5 1800+63",
      "Be7 1662+112",
      "exf6 1783+39",
      "Bxf6 1677+22",
      "O-O 1763+51",
      "O-O 1702+6",
      "Qd3 1730+61",
      "Nb4 1705+27",
      "Qd2 1727+33",
      "d6 1696+40",
      "a3 1738+19",
      "Nc6 +26",
      "Ng5 +36",
      "Bxg5 1617+41",
      "Qxg5 1746+26",
      "Qd7 1614+23",
      "Bd3 1745+42",
      "f6 1607+35",
      "Qh4 1484+293",
      "g5 1547+79",
      "Qxh7+ 1509+16",
      "Qxh7 1465+126",
      "Bxh7+ 1532+60",
      "Kxh7 1425+10",
      "Nb5 1551+71",
      "Rf7 1410+37",
      "Rd1 1530+57",
      "Rg7 1419+20",
      "d5 1481+83",
      "Ne5 1426+23",
      "Nd4 1469+41",
      "Rg6 1430+21",
      "Nxe6 1488+17",
      "Rh6 +45",
      "f4 1495+1",
      "Nf3+ +118",
      "gxf3 1367+137",
      "Bxe6 1317+42",
      "dxe6 1389+49",
      "Re8 1327+14",
      "fxg5 1365+60",
      "fxg5 1317+23",
      "Bxg5 1390+22",
      "Rg6 1325+22",
      "h4 1363+56",
      "Rexe6 1347+9",
      "Re1 1350+42",
      "Re5 1254+117",
      "Rxe5 1351+35",
      "dxe5 1266+19",
      "Rd1 1369+12",
      "Rc6 1262+26",
      "c3 1394+13",
      "Rb6 1271+17",
      "Rd7+ 1416+11",
      "Kg6 1283+12",
      "Bc1 1413+40",
      "c5 1289+20",
      "Rd5 1438+10",
      "Kh5 1287+30",
      "Rxe5+ 1455+14",
      "Kxh4 1300+10",
      "Rxc5 1474+18",
      "Rg6+ 1321+8",
      "Kf2 1490+14",
      "Rg3 1326+26",
      "Rh5+ 1492+26",
      "Kxh5 1333+11",
      "Kxg3 1521",
      "b5 1348+24",
      "Kf4 1549+7",
      "a5 1346+32",
      "Ke5 1571+8",
      "Kh4 1354+19",
      "Kd5 1594+10",
      "Kg3 1354+27",
      "Kc5 1616+12",
      "Kxf3 1360+23",
      "Kxb5 1638+9",
      "Ke2 1374+12",
      "c4 1667+6",
      "Kd1 +8",
      "a4 +9",
      "Kxc1 1351+34",
      "c5 1692+6",
      "Kxb2 1349+31",
      "c6 +33",
      "Kc3 1348",
      "c7 1740+11",
      "Kd4 1356+15",
      "c8=Q +18",
      "Kd5 +31",
      "Qe8 1718+9",
      "Kd6 1357+24",
      "Qe4 1736+18",
      "Kc7 1367+20",
      "Qe6 1755+11",
      "Kb7 1375+12",
      "Qd7+ 1769+27",
      "Kb8 +15",
      "Kb6 +6",
      "Ka8 1365+11",
      "Qc8# 1796+10"
    )

    val expected =
      """d4 { [%clk 0:30:52] } e6 { [%clk 0:30:18] } Nf3 { [%clk 0:31:02] } Nc6 { [%clk 0:30:21] } e4 { [%clk 0:30:51] } Bb4+ { [%clk 0:29:01] } Nc3 { [%clk 0:31:11] } Bd6 { [%clk 0:28:56] } Bc4 { [%clk 0:30:31] } Nf6 { [%clk 0:29:02] } e5 { [%clk 0:30:00] } Be7 { [%clk 0:27:42] } exf6 { [%clk 0:29:43] } Bxf6 { [%clk 0:27:57] } O-O { [%clk 0:29:23] } O-O { [%clk 0:28:22] } Qd3 { [%clk 0:28:50] } Nb4 { [%clk 0:28:25] } Qd2 { [%clk 0:28:47] } d6 { [%clk 0:28:16] } a3 { [%clk 0:28:58] } Nc6 Ng5 Bxg5 { [%clk 0:26:57] } Qxg5 { [%clk 0:29:06] } Qd7 { [%clk 0:26:54] } Bd3 { [%clk 0:29:05] } f6 { [%clk 0:26:47] } Qh4 { [%clk 0:24:44] } g5 { [%clk 0:25:47] } Qxh7+ { [%clk 0:25:09] } Qxh7 { [%clk 0:24:25] } Bxh7+ { [%clk 0:25:32] } Kxh7 { [%clk 0:23:45] } Nb5 { [%clk 0:25:51] } Rf7 { [%clk 0:23:30] } Rd1 { [%clk 0:25:30] } Rg7 { [%clk 0:23:39] } d5 { [%clk 0:24:41] } Ne5 { [%clk 0:23:46] } Nd4 { [%clk 0:24:29] } Rg6 { [%clk 0:23:50] } Nxe6 { [%clk 0:24:48] } Rh6 f4 { [%clk 0:24:55] } Nf3+ gxf3 { [%clk 0:22:47] } Bxe6 { [%clk 0:21:57] } dxe6 { [%clk 0:23:09] } Re8 { [%clk 0:22:07] } fxg5 { [%clk 0:22:45] } fxg5 { [%clk 0:21:57] } Bxg5 { [%clk 0:23:10] } Rg6 { [%clk 0:22:05] } h4 { [%clk 0:22:43] } Rexe6 { [%clk 0:22:27] } Re1 { [%clk 0:22:30] } Re5 { [%clk 0:20:54] } Rxe5 { [%clk 0:22:31] } dxe5 { [%clk 0:21:06] } Rd1 { [%clk 0:22:49] } Rc6 { [%clk 0:21:02] } c3 { [%clk 0:23:14] } Rb6 { [%clk 0:21:11] } Rd7+ { [%clk 0:23:36] } Kg6 { [%clk 0:21:23] } Bc1 { [%clk 0:23:33] } c5 { [%clk 0:21:29] } Rd5 { [%clk 0:23:58] } Kh5 { [%clk 0:21:27] } Rxe5+ { [%clk 0:24:15] } Kxh4 { [%clk 0:21:40] } Rxc5 { [%clk 0:24:34] } Rg6+ { [%clk 0:22:01] } Kf2 { [%clk 0:24:50] } Rg3 { [%clk 0:22:06] } Rh5+ { [%clk 0:24:52] } Kxh5 { [%clk 0:22:13] } Kxg3 { [%clk 0:25:21] } b5 { [%clk 0:22:28] } Kf4 { [%clk 0:25:49] } a5 { [%clk 0:22:26] } Ke5 { [%clk 0:26:11] } Kh4 { [%clk 0:22:34] } Kd5 { [%clk 0:26:34] } Kg3 { [%clk 0:22:34] } Kc5 { [%clk 0:26:56] } Kxf3 { [%clk 0:22:40] } Kxb5 { [%clk 0:27:18] } Ke2 { [%clk 0:22:54] } c4 { [%clk 0:27:47] } Kd1 a4 Kxc1 { [%clk 0:22:31] } c5 { [%clk 0:28:12] } Kxb2 { [%clk 0:22:29] } c6 Kc3 { [%clk 0:22:28] } c7 { [%clk 0:29:00] } Kd4 { [%clk 0:22:36] } c8=Q Kd5 Qe8 { [%clk 0:28:38] } Kd6 { [%clk 0:22:37] } Qe4 { [%clk 0:28:56] } Kc7 { [%clk 0:22:47] } Qe6 { [%clk 0:29:15] } Kb7 { [%clk 0:22:55] } Qd7+ { [%clk 0:29:29] } Kb8 Kb6 Ka8 { [%clk 0:22:45] } Qc8# { [%clk 0:29:56] }"""

    val game = DgtJson.GameJson(moves, None)
    assertEquals(game.toPgn(Tags.empty).value.trim, expected)
