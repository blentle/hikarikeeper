package org.hikarikeeper.core;

/**
 * @desc uniform exception for Hikarikeeper
 */
public abstract class HikariException extends Exception {

    /**
     * todo: may be enum
     */
    private String opcode;

    /**
     * a description for exception
     */
    private String desc;


}
