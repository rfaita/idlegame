package com.idle.game.server.scalar;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseValueException;
import graphql.schema.GraphQLScalarType;
import java.util.Date;

/**
 *
 * @author rafael
 */
public class GraphQLDate extends GraphQLScalarType {

    public GraphQLDate() {
        super("Date", "Date type", new Coercing<Date, Long>() {

            @Override
            public Long serialize(Object input) {
                if (input instanceof Date) {
                    return ((Date) input).getTime();
                } else {
                    return null;
                }

            }

            @Override
            public Date parseValue(Object input) {
                if (input instanceof Long) {
                    return new Date((Long) input);
                } else {
                    throw new CoercingParseValueException("Invalid value '" + input + "' for Date");
                }
            }

            @Override
            public Date parseLiteral(Object input) {
                if (!(input instanceof StringValue)) {
                    return null;
                }
                String value = ((StringValue) input).getValue();

                return new Date(new Long(value));
            }
        });
    }

}
