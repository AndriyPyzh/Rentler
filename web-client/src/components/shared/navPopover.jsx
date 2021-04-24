import React, { useRef, useState } from 'react';
import { Overlay, Popover } from "react-bootstrap";

const NavPopover = ({ title, text, classes, children }) => {
    const [show, setShow] = useState(false);
    const [target, setTarget] = useState(null);
    const ref = useRef(null);

    const handleClick = (event) => {
        setShow(!show);
        setTarget(event.target);
    };

    return (
        <div ref={ ref }>
            <button className={ classes } onClick={ handleClick }>{ text }</button>
            <Overlay
                show={ show }
                target={ target }
                placement="bottom"
                container={ ref.current }
                containerPadding={ 20 }
            >
                <Popover id="popover-contained">
                    <Popover.Title as="h3">{ title }</Popover.Title>
                    <Popover.Content onClick={ handleClick }>
                        { children }
                    </Popover.Content>
                </Popover>
            </Overlay>
        </div>
    );
};

export default NavPopover;
