/* Generated By:JavaCC: Do not edit this line. SSHConfigsReaderTokenManager.java */
package edu.casetools.mreasoner.vera.sensors.ssh.configs.compiler;

/** Token Manager. */
@SuppressWarnings("all")
public class SSHConfigsReaderTokenManager implements SSHConfigsReaderConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x800000L) != 0L)
            return 0;
         if ((active0 & 0x60000000L) != 0L)
         {
            jjmatchedKind = 26;
            return -1;
         }
         return -1;
      case 1:
         if ((active0 & 0x60000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 26;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 2:
         if ((active0 & 0x60000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 26;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 3:
         if ((active0 & 0x60000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 26;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 4:
         if ((active0 & 0x40000000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 26;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 34:
         return jjStopAtPos(0, 9);
      case 40:
         return jjStopAtPos(0, 14);
      case 41:
         return jjStopAtPos(0, 15);
      case 44:
         return jjStopAtPos(0, 17);
      case 45:
         return jjStartNfaWithStates_0(0, 23, 0);
      case 46:
         return jjStopAtPos(0, 16);
      case 47:
         jjmatchedKind = 19;
         return jjMoveStringLiteralDfa1_0(0x40L);
      case 58:
         return jjStopAtPos(0, 18);
      case 60:
         jjmatchedKind = 21;
         return jjMoveStringLiteralDfa1_0(0x1ff80000000L);
      case 62:
         return jjStopAtPos(0, 22);
      case 92:
         return jjStopAtPos(0, 20);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x40000000L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x20000000L);
      default :
         return jjMoveNfa_0(3, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 42:
         if ((active0 & 0x40L) != 0L)
            return jjStopAtPos(1, 6);
         break;
      case 47:
         return jjMoveStringLiteralDfa2_0(active0, 0x15500000000L);
      case 72:
         return jjMoveStringLiteralDfa2_0(active0, 0x80000000L);
      case 80:
         return jjMoveStringLiteralDfa2_0(active0, 0x2200000000L);
      case 83:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000000000L);
      case 85:
         return jjMoveStringLiteralDfa2_0(active0, 0x800000000L);
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x40000000L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x20000000L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000000000L);
      case 72:
         return jjMoveStringLiteralDfa3_0(active0, 0x100000000L);
      case 73:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000000000L);
      case 79:
         return jjMoveStringLiteralDfa3_0(active0, 0x280000000L);
      case 80:
         return jjMoveStringLiteralDfa3_0(active0, 0x4400000000L);
      case 83:
         return jjMoveStringLiteralDfa3_0(active0, 0x10800000000L);
      case 85:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000000000L);
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x40000000L);
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x20000000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa4_0(active0, 0x4000000000L);
      case 69:
         return jjMoveStringLiteralDfa4_0(active0, 0x800000000L);
      case 73:
         return jjMoveStringLiteralDfa4_0(active0, 0x10000000000L);
      case 76:
         return jjMoveStringLiteralDfa4_0(active0, 0x8000000000L);
      case 79:
         return jjMoveStringLiteralDfa4_0(active0, 0x500000000L);
      case 82:
         return jjMoveStringLiteralDfa4_0(active0, 0x200000000L);
      case 83:
         return jjMoveStringLiteralDfa4_0(active0, 0x3080000000L);
      case 101:
         if ((active0 & 0x20000000L) != 0L)
            return jjStopAtPos(3, 29);
         break;
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x40000000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 69:
         return jjMoveStringLiteralDfa5_0(active0, 0x9000000000L);
      case 76:
         return jjMoveStringLiteralDfa5_0(active0, 0x10000000000L);
      case 82:
         return jjMoveStringLiteralDfa5_0(active0, 0xc00000000L);
      case 83:
         return jjMoveStringLiteralDfa5_0(active0, 0x6100000000L);
      case 84:
         return jjMoveStringLiteralDfa5_0(active0, 0x280000000L);
      case 101:
         if ((active0 & 0x40000000L) != 0L)
            return jjStopAtPos(4, 30);
         break;
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 62:
         if ((active0 & 0x200000000L) != 0L)
            return jjStopAtPos(5, 33);
         break;
      case 69:
         return jjMoveStringLiteralDfa6_0(active0, 0x10000000000L);
      case 78:
         return jjMoveStringLiteralDfa6_0(active0, 0x8880000000L);
      case 82:
         return jjMoveStringLiteralDfa6_0(active0, 0x1000000000L);
      case 83:
         return jjMoveStringLiteralDfa6_0(active0, 0x4000000000L);
      case 84:
         return jjMoveStringLiteralDfa6_0(active0, 0x500000000L);
      case 87:
         return jjMoveStringLiteralDfa6_0(active0, 0x2000000000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 62:
         if ((active0 & 0x400000000L) != 0L)
            return jjStopAtPos(6, 34);
         break;
      case 65:
         return jjMoveStringLiteralDfa7_0(active0, 0x880000000L);
      case 67:
         return jjMoveStringLiteralDfa7_0(active0, 0x8000000000L);
      case 78:
         return jjMoveStringLiteralDfa7_0(active0, 0x11100000000L);
      case 79:
         return jjMoveStringLiteralDfa7_0(active0, 0x2000000000L);
      case 87:
         return jjMoveStringLiteralDfa7_0(active0, 0x4000000000L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa8_0(active0, 0x1100000000L);
      case 67:
         return jjMoveStringLiteralDfa8_0(active0, 0x10000000000L);
      case 69:
         return jjMoveStringLiteralDfa8_0(active0, 0x8000000000L);
      case 77:
         return jjMoveStringLiteralDfa8_0(active0, 0x880000000L);
      case 79:
         return jjMoveStringLiteralDfa8_0(active0, 0x4000000000L);
      case 82:
         return jjMoveStringLiteralDfa8_0(active0, 0x2000000000L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 62:
         if ((active0 & 0x8000000000L) != 0L)
            return jjStopAtPos(8, 39);
         break;
      case 68:
         return jjMoveStringLiteralDfa9_0(active0, 0x2000000000L);
      case 69:
         return jjMoveStringLiteralDfa9_0(active0, 0x10880000000L);
      case 77:
         return jjMoveStringLiteralDfa9_0(active0, 0x1100000000L);
      case 82:
         return jjMoveStringLiteralDfa9_0(active0, 0x4000000000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 62:
         if ((active0 & 0x80000000L) != 0L)
            return jjStopAtPos(9, 31);
         else if ((active0 & 0x800000000L) != 0L)
            return jjStopAtPos(9, 35);
         else if ((active0 & 0x2000000000L) != 0L)
            return jjStopAtPos(9, 37);
         else if ((active0 & 0x10000000000L) != 0L)
            return jjStopAtPos(9, 40);
         break;
      case 68:
         return jjMoveStringLiteralDfa10_0(active0, 0x4000000000L);
      case 69:
         return jjMoveStringLiteralDfa10_0(active0, 0x1100000000L);
      default :
         break;
   }
   return jjStartNfa_0(8, active0);
}
private int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(8, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0);
      return 10;
   }
   switch(curChar)
   {
      case 62:
         if ((active0 & 0x100000000L) != 0L)
            return jjStopAtPos(10, 32);
         else if ((active0 & 0x1000000000L) != 0L)
            return jjStopAtPos(10, 36);
         else if ((active0 & 0x4000000000L) != 0L)
            return jjStopAtPos(10, 38);
         break;
      default :
         break;
   }
   return jjStartNfa_0(9, active0);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 11;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 3:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 25)
                        kind = 25;
                  }
                  else if (curChar == 39)
                     jjCheckNAddTwoStates(9, 10);
                  else if (curChar == 45)
                     jjstateSet[jjnewStateCnt++] = 0;
                  break;
               case 0:
                  if (curChar == 45)
                     jjCheckNAddTwoStates(1, 2);
                  break;
               case 1:
                  if ((0xffffffffffffdbffL & l) != 0L)
                     jjCheckNAddTwoStates(1, 2);
                  break;
               case 2:
                  if ((0x2400L & l) != 0L && kind > 5)
                     kind = 5;
                  break;
               case 5:
                  if ((0x3ff000000000000L & l) != 0L && kind > 25)
                     kind = 25;
                  break;
               case 8:
                  if (curChar == 39)
                     jjCheckNAddTwoStates(9, 10);
                  break;
               case 9:
                  if ((0xffffff7fffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(9, 10);
                  break;
               case 10:
                  if (curChar == 39 && kind > 28)
                     kind = 28;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 3:
                  if ((0x7fffffeL & l) != 0L)
                  {
                     if (kind > 27)
                        kind = 27;
                  }
                  else if ((0x7fffffe00000000L & l) != 0L)
                  {
                     if (kind > 26)
                        kind = 26;
                  }
                  else if (curChar == 95)
                  {
                     if (kind > 24)
                        kind = 24;
                  }
                  break;
               case 1:
                  jjAddStates(0, 1);
                  break;
               case 4:
                  if (curChar == 95 && kind > 24)
                     kind = 24;
                  break;
               case 6:
                  if ((0x7fffffe00000000L & l) != 0L && kind > 26)
                     kind = 26;
                  break;
               case 7:
                  if ((0x7fffffeL & l) != 0L && kind > 27)
                     kind = 27;
                  break;
               case 9:
                  jjAddStates(2, 3);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(0, 1);
                  break;
               case 9:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(2, 3);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 11 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_3()
{
   return jjMoveNfa_3(0, 0);
}
private int jjMoveNfa_3(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 1;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x800400000000L & l) != 0L)
                     kind = 13;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x14404410000000L & l) != 0L)
                     kind = 13;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 1 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private final int jjStopStringLiteralDfa_2(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_2(int pos, long active0)
{
   return jjMoveNfa_2(jjStopStringLiteralDfa_2(pos, active0), pos + 1);
}
private int jjMoveStringLiteralDfa0_2()
{
   switch(curChar)
   {
      case 92:
         return jjStopAtPos(0, 10);
      default :
         return jjMoveNfa_2(0, 0);
   }
}
private int jjMoveNfa_2(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 2;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xfffffffbffffffffL & l) != 0L)
                  {
                     if (kind > 12)
                        kind = 12;
                  }
                  else if (curChar == 34)
                  {
                     if (kind > 11)
                        kind = 11;
                  }
                  break;
               case 1:
                  if ((0xfffffffbffffffffL & l) != 0L)
                     kind = 12;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xffffffffefffffffL & l) != 0L)
                     kind = 12;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((jjbitVec0[i2] & l2) != 0L && kind > 12)
                     kind = 12;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 2 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_1()
{
   switch(curChar)
   {
      case 42:
         return jjMoveStringLiteralDfa1_1(0x80L);
      default :
         return 1;
   }
}
private int jjMoveStringLiteralDfa1_1(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 1;
   }
   switch(curChar)
   {
      case 47:
         if ((active0 & 0x80L) != 0L)
            return jjStopAtPos(1, 7);
         break;
      default :
         return 2;
   }
   return 2;
}
static final int[] jjnextStates = {
   1, 2, 9, 10, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, "\42", null, null, null, 
null, "\50", "\51", "\56", "\54", "\72", "\57", "\134", "\74", "\76", "\55", null, 
null, null, null, null, "\164\162\165\145", "\146\141\154\163\145", 
"\74\110\117\123\124\116\101\115\105\76", "\74\57\110\117\123\124\116\101\115\105\76", "\74\120\117\122\124\76", 
"\74\57\120\117\122\124\76", "\74\125\123\105\122\116\101\115\105\76", 
"\74\57\125\123\105\122\116\101\115\105\76", "\74\120\101\123\123\127\117\122\104\76", 
"\74\57\120\101\123\123\127\117\122\104\76", "\74\123\111\114\105\116\103\105\76", "\74\57\123\111\114\105\116\103\105\76", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
   "INSIDE_COMMENT",
   "STRING_STATE",
   "ESC_STATE",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, -1, 1, 0, -1, 2, 3, 0, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
};
static final long[] jjtoToken = {
   0x1fffffffa01L, 
};
static final long[] jjtoSkip = {
   0x1feL, 
};
static final long[] jjtoMore = {
   0x400L, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[11];
private final int[] jjstateSet = new int[22];
protected char curChar;
/** Constructor. */
public SSHConfigsReaderTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public SSHConfigsReaderTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 11; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 4 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   for (;;)
   {
     switch(curLexState)
     {
       case 0:
         try { input_stream.backup(0);
            while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
               curChar = input_stream.BeginToken();
         }
         catch (java.io.IOException e1) { continue EOFLoop; }
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_0();
         break;
       case 1:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_1();
         if (jjmatchedPos == 0 && jjmatchedKind > 8)
         {
            jjmatchedKind = 8;
         }
         break;
       case 2:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_2();
         break;
       case 3:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_3();
         break;
     }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
        if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           matchedToken = jjFillToken();
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           return matchedToken;
        }
        else if ((jjtoSkip[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
         if (jjnewLexState[jjmatchedKind] != -1)
           curLexState = jjnewLexState[jjmatchedKind];
           continue EOFLoop;
        }
      if (jjnewLexState[jjmatchedKind] != -1)
        curLexState = jjnewLexState[jjmatchedKind];
        curPos = 0;
        jjmatchedKind = 0x7fffffff;
        try {
           curChar = input_stream.readChar();
           continue;
        }
        catch (java.io.IOException e1) { }
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try { input_stream.readChar(); input_stream.backup(1); }
     catch (java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
     }
     throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
   }
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

}
