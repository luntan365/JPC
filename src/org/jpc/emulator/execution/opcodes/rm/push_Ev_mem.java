package org.jpc.emulator.execution.opcodes.rm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import static org.jpc.emulator.processor.Processor.*;

public class push_Ev_mem extends Executable
{
    final Address op1;
    final int size;

    public push_Ev_mem(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        size = parent.operand[0].size;
        op1 = new Address(parent.operand[0]);
    }

    public Branch execute(Processor cpu)
    {
        if (size == 16)
        {
        cpu.push16((short)op1.get16(cpu));
        }
        else if (size == 32)
        {
        cpu.push32(op1.get32(cpu));
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