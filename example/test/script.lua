mainPage = action_wheel:newPage()
action_wheel:setPage(mainPage)

action = mainPage:newAction()
    :title("My Action")
    :item("minecraft:stick")
    :hoverColor(1, 0, 1)
    :onLeftClick(function()
        log(screen:setScreenToInteractic())
    end)
--function events.SCREEN_RENDER(ctx)
--        if(screen:isCurrentScreenInteractic()) then
--            log(ctx)
--        end
--    end
function events.SCREEN_TICK()
    if(screen:isCurrentScreenInteractic()) then
        -- ничего | nothing
    end
end
function events.MOUSE_PRESS(ctx)
    if(screen:isCurrentScreenInteractic()) then
        log(ctx)
    end
end
function events.KEY_PRESS(ctx)
    if(screen:isCurrentScreenInteractic()) then
        log(ctx)
    end
end
function events.KEY_RELEASE(ctx)
    if(screen:isCurrentScreenInteractic()) then
        log(ctx)
    end
end
function events.MOUSE_RELEASE(ctx)
    if(screen:isCurrentScreenInteractic()) then
        log(ctx)
    end
end
function events.MOUSE_DRAGGED(ctx)
    if(screen:isCurrentScreenInteractic()) then
        log(ctx)
    end
end
function events.MOUSE_SCROLLED(ctx)
    if(screen:isCurrentScreenInteractic()) then
        log(ctx)
    end
end
function events.MOUSE_MOVED(ctx)
    if(screen:isCurrentScreenInteractic()) then
        log(ctx)
    end
end
aa = screen:getCurrentScreen()
ba = screen:isCurrentScreenInteractic()
ca = screen:setScreenToInteractic()
da = screen:getCurrentScreenWidth()
ea = screen:getCurrentScreenHeight()
fa = screen:getCurrentWindowHeight()
ga = screen:getCurrentWindowWidth()
ha = screen:getCurrentWindowScaledHeight()
ia = screen:getCurrentWindowScaledWidth()
ja = screen:getCurrentWindowPosition()