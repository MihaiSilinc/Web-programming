/*
$(document).ready(() => {
    let oldX, oldY;
    let draggingElement;

    $(".draggable").bind("mousedown", (e) => {
        e.preventDefault();
        oldX = e.clientX;
        oldY = e.clientY;
        draggingElement = e.target;
        $(draggingElement).css("position", "absolute");

        $(document).bind("mouseup", () => {
            $(document).unbind("mouseup");
            $(document).unbind("mousemove");
            draggingElement = null;
        })

        $(document).bind("mousemove", (e) => {
            e.preventDefault();

            let diffX, diffY;
            diffX = oldX - e.clientX;
            diffY = oldY - e.clientY;
            oldX = e.clientX;
            oldY = e.clientY;

            let jDraggingElement = $(draggingElement);
            jDraggingElement.css({
                top: (jDraggingElement.offset().top - diffY) + "px",
                left: (jDraggingElement.offset().left - diffX) + "px"
            });
        });
    });
})*/
function clearClassWithException(clearedClass, exception) {
    $(".stack-space").filter(e => e !== exception).removeClass(clearedClass);
}

function autoArrangeOnDrop() {
    let stack_spaces = $(".stack-space");
    for (let i = stack_spaces.length - 1; i >= 0; i--) {
        if ($(stack_spaces[i]).children().length === 0) {
            let newChild = null;
            for (let j = i - 1; j >= 0; j--) {
                if ($(stack_spaces[j]).children().length === 1) {
                    newChild = $(stack_spaces[j]).children()[0];
                    break;
                }
            }
            if (newChild == null)
                break;
            $(stack_spaces[i]).append(newChild);
        }
    }
}

function autoArrangeOnDrag(element) {
    let stack_spaces = $(".stack-space");
    let firstPos = 0;
    let lastPos = 0;
    while (firstPos < $(stack_spaces).length && $(stack_spaces[firstPos]).children().length === 0)
        firstPos++;
    lastPos = firstPos;
    while (lastPos < $(stack_spaces).length && $(stack_spaces[lastPos]).children()[0] !== element) {
        lastPos++;
    }
    lastPos = Math.min(lastPos, $(stack_spaces).length);
    let clone = $($(stack_spaces[firstPos]).children()[0]).clone();
    $(clone).removeAttr("draggable");
    $(clone).addClass("fake");
    for(let i = firstPos; i <= lastPos; i++) {
        $(stack_spaces[i - 1]).append($(stack_spaces[i]).children()[0]);
    }
    $(stack_spaces[lastPos]).append(clone);
}

$(document).ready(() => {
    let draggingElement = null;
    $(document)
        .on("dragstart", jEvent => {
            let event = jEvent.originalEvent;
            let target = event.target;
            if ($(target).hasClass("draggable")) {
                draggingElement = target;
                return;
            }
            jEvent.preventDefault();
        })
        .on("drop", jEvent => {
            let event = jEvent.originalEvent;
            let target = event.target;
            if ($(target).hasClass("stack-space")) {
                jEvent.preventDefault();
                target.appendChild(draggingElement);
                clearClassWithException("choice");
                autoArrangeOnDrop();
            }
            if ($(target).hasClass("fake")) {
                jEvent.preventDefault();
                $(target).parent().append(draggingElement);
                clearClassWithException("choice");
                autoArrangeOnDrop();
                $(".fake").remove();
            }

        })
        .on("dragover", jEvent => {
            let event = jEvent.originalEvent;
            let target = event.target;
            if ($(target).hasClass("stack-space")) {
                jEvent.preventDefault();
                clearClassWithException("choice", target);
                $(target).addClass("choice");
            } else if ($(target).hasClass("draggable") && $(target).parent().hasClass("stack-space")) {
                clearClassWithException("choice", $(target).parent());
                $(target).parent().addClass("choice");
                jEvent.preventDefault();
                if ($(target).hasClass("fake") === false) {
                    autoArrangeOnDrag(target);
                }
            }
        })
        .on("dragleave", jEvent => {
            let event = jEvent.originalEvent;
            let target = event.target;
            if ($(target).hasClass("stack-space")) {
                jEvent.preventDefault();
                autoArrangeOnDrop();
                clearClassWithException("choice");
            } else if ($(target).hasClass("fake")) {
                jEvent.preventDefault();
                $(".fake").remove();
                autoArrangeOnDrop();
                clearClassWithException("choice");
            } else {
                $("body").append(draggingElement);
                autoArrangeOnDrop();
            }
        });
});