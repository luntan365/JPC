package org.jpc.emulator.execution.opcodes.rm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import static org.jpc.emulator.processor.Processor.*;

public class jp_Jb extends Executable
{
    final int jmp;
    final int blockLength;
    final int instructionLength;

    public jp_Jb(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        blockLength = parent.x86Length+(int)parent.eip-blockStart;
        instructionLength = parent.x86Length;
        jmp = (byte)parent.operand[0].lval;
    }

    public Branch execute(Processor cpu)
    {
        if (cpu.pf())
            {
            cpu.eip += jmp+blockLength;
            return Branch.T1;
        }
        else
        {
            cpu.eip += blockLength;
            return Branch.T2;
        }
    }

    public boolean isBranch()
    {
        return true;
    }

    public String toString()
    {
        return this.getClass().getName();
    }
}