package it.codingjam.mcp.server.models;

import java.util.List;

public record ListResult<T>(
        List<T> results
) {
}
