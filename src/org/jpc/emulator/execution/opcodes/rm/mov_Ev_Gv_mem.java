package org.jpc.emulator.execution.opcodes.rm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import static org.jpc.emulator.processor.Processor.*;

public class mov_Ev_Gv_mem extends Executable
{
    final Address op1;
    final int op2Index;
    final int size;

    public mov_Ev_Gv_mem(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        size = parent.operand[0].size;
        op1 = new Address(parent.operand[0]);
        op2Index = Processor.getRegIndex(parent.operand[1].toString());
    }

    public Branch execute(Processor cpu)
    {
        Reg op2 = cpu.regs[op2Index];
        if (size == 16)
        {
        op1.set16(cpu, (short)op2.get16());
        }
        else if (size == 32)
        {
        op1.set32(cpu, op2.get16());
        }
        return Branch.None;
    }

    public boolean isBranch()
    {
        return false;
    }

    public String toString()
    {
        return this.getClass().getName();
    }
}